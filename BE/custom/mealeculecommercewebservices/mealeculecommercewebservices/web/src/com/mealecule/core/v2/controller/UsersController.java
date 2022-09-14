/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.mealecule.core.v2.controller;

import de.hybris.platform.commercefacades.address.AddressVerificationFacade;
import de.hybris.platform.commercefacades.address.data.AddressVerificationResult;
import de.hybris.platform.commercefacades.customergroups.CustomerGroupFacade;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoData;
import de.hybris.platform.commercefacades.order.data.CCPaymentInfoDatas;
import de.hybris.platform.commercefacades.order.data.OrderHistoriesData;
import de.hybris.platform.commercefacades.order.data.OrderHistoryData;
import de.hybris.platform.commercefacades.user.data.AddressData;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.RegisterData;
import de.hybris.platform.commercefacades.user.data.UserGroupDataList;
import de.hybris.platform.commercefacades.user.exceptions.PasswordMismatchException;
import de.hybris.platform.commerceservices.address.AddressVerificationDecision;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.search.pagedata.SearchPageData;
import de.hybris.platform.commercewebservicescommons.dto.order.PaymentDetailsListWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.order.PaymentDetailsWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.user.AddressListWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.user.AddressValidationWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.user.AddressWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.user.UserGroupListWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.user.UserSignUpWsDTO;
import de.hybris.platform.commercewebservicescommons.dto.user.UserWsDTO;
import de.hybris.platform.commercewebservicescommons.errors.exceptions.RequestParameterException;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.GenericSearchConstants.LOG;
import de.hybris.platform.core.PK.PKException;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.webservicescommons.cache.CacheControl;
import de.hybris.platform.webservicescommons.cache.CacheControlDirective;
import de.hybris.platform.webservicescommons.dto.error.ErrorListWsDTO;
import de.hybris.platform.webservicescommons.dto.error.ErrorWsDTO;
import de.hybris.platform.webservicescommons.errors.exceptions.WebserviceValidationException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriUtils;

import com.mealecule.core.constants.YcommercewebservicesConstants;
import com.mealecule.core.populator.HttpRequestCustomerDataPopulator;
import com.mealecule.core.populator.options.PaymentInfoOption;
import com.mealecule.core.user.data.AddressDataList;
import com.mealecule.core.user.data.CustomerGameData;
import com.mealecule.core.user.data.CustomerGameWsDTO;
import com.mealecule.core.user.data.PreferredMealeculeData;
import com.mealecule.core.user.data.PreferredMealeculeWsDTO;
import com.mealecule.core.validation.data.AddressValidationData;
import com.mealecule.facades.customer.MealeculeCustomerFacade;


/**
 * Main Controller for Users
 *
 * @pathparam userId User identifier or one of the literals below :
 *            <ul>
 *            <li>'current' for currently authenticated user</li>
 *            <li>'anonymous' for anonymous user</li>
 *            </ul>
 * @pathparam addressId Address identifier
 * @pathparam paymentDetailsId - Payment details identifier
 */
@Controller
@RequestMapping(value = "/{baseSiteId}/users")
@CacheControl(directive = CacheControlDirective.PRIVATE)
public class UsersController extends BaseCommerceController
{
	private static final Logger LOG = Logger.getLogger(UsersController.class);
	@Resource(name = "mealeculeCustomerFacade")
	private MealeculeCustomerFacade mealeculeCustomerFacade;
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "modelService")
	private ModelService modelService;
	@Resource(name = "customerGroupFacade")
	private CustomerGroupFacade customerGroupFacade;
	@Resource(name = "addressVerificationFacade")
	private AddressVerificationFacade addressVerificationFacade;
	@Resource(name = "httpRequestCustomerDataPopulator")
	private HttpRequestCustomerDataPopulator httpRequestCustomerDataPopulator;
	@Resource(name = "HttpRequestUserSignUpDTOPopulator")
	private Populator<HttpServletRequest, UserSignUpWsDTO> httpRequestUserSignUpDTOPopulator;
	@Resource(name = "addressDataErrorsPopulator")
	private Populator<AddressVerificationResult<AddressVerificationDecision>, Errors> addressDataErrorsPopulator;
	@Resource(name = "validationErrorConverter")
	private Converter<Object, List<ErrorWsDTO>> validationErrorConverter;
	@Resource(name = "putUserDTOValidator")
	private Validator putUserDTOValidator;
	@Resource(name = "userSignUpDTOValidator")
	private Validator userSignUpDTOValidator;
	@Resource(name = "guestConvertingDTOValidator")
	private Validator guestConvertingDTOValidator;
	@Resource(name = "passwordStrengthValidator")
	private Validator passwordStrengthValidator;

	@Resource(name = "customerConverter")
	private Converter<UserModel, CustomerData> customerConverter;

	/**
	 * Registers a customer. The following two sets of parameters are available:
	 * <ul>
	 * <li>First set is used to register a customer. In this case the required parameters are: login, password, firstName,
	 * lastName, titleCode.</li>
	 * <li>Second set is used to convert a guest to a customer. In this case the required parameters are: guid, password.
	 * </li>
	 * <ul>
	 *
	 * @param login
	 *           the login
	 * @param password
	 *           the password
	 * @param titleCode
	 *           the title code
	 * @param firstName
	 *           the first name
	 * @param lastName
	 *           the last name
	 * @param guid
	 *           the guid
	 * @param fields
	 *           the fields
	 * @param httpRequest
	 *           the http request
	 * @param httpResponse
	 *           the http response
	 * @return registered customer
	 * @throws DuplicateUidException
	 *            the duplicate uid exception
	 * @throws WebserviceValidationException
	 *            the webservice validation exception
	 * @throws RequestParameterException
	 *            in case given parameters are invalid
	 * @throws UnsupportedEncodingException
	 *            if there utf-8 is not supported when attempting to encode url segments.
	 * @formparam login Customer's login. Customer login is case insensitive.
	 * @formparam password Customer's password.
	 * @formparam firstName Customer's first name.
	 * @formparam lastName Customer's last name.
	 * @formparam titleCode Customer's title code. For a list of codes, see /{baseSiteId}/titles resource
	 * @formparam guid Guest order's guid.
	 * @security Permitted only for customer managers, clients or trusted clients.
	 */
	@Secured(
	{ "ROLE_CLIENT", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	@SuppressWarnings("squid:S1160")
	public UserWsDTO registerUser(@RequestParam(required = false) final String login, @RequestParam final String password,
			@RequestParam(required = false) final String titleCode, @RequestParam(required = false) final String firstName,
			@RequestParam(required = false) final String lastName, @RequestParam(required = false) final String guid,
			@RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields, final HttpServletRequest httpRequest,
			final HttpServletResponse httpResponse)
			throws DuplicateUidException, RequestParameterException, WebserviceValidationException, UnsupportedEncodingException //NOSONAR
	{
		final UserSignUpWsDTO user = new UserSignUpWsDTO();
		httpRequestUserSignUpDTOPopulator.populate(httpRequest, user);
		CustomerData customer = null;
		String userId = login;
		if (guid != null)
		{
			validate(user, "user", guestConvertingDTOValidator);
			convertToCustomer(password, guid);
			customer = mealeculeCustomerFacade.getCurrentCustomer();
			userId = customer.getUid();
		}
		else
		{
			validate(user, "user", userSignUpDTOValidator);
			registerNewUser(login, password, titleCode, firstName, lastName);
			customer = mealeculeCustomerFacade.getUserForUID(userId);
		}
		httpResponse.setHeader(YcommercewebservicesConstants.LOCATION, getAbsoluteLocationURL(httpRequest, userId));
		return getDataMapper().map(customer, UserWsDTO.class, fields);
	}

	/**
	 * Registers a customer.
	 *
	 * @param user
	 *           User's object
	 * @param fields
	 *           the fields
	 * @param httpRequest
	 *           the http request
	 * @param httpResponse
	 *           the http response
	 * @return registered customer
	 * @throws DuplicateUidException
	 *            the duplicate uid exception
	 * @throws UnknownIdentifierException
	 *            if the title code is invalid
	 * @throws IllegalArgumentException
	 *            the illegal argument exception
	 * @throws WebserviceValidationException
	 *            if any filed is invalid
	 * @throws UnsupportedEncodingException
	 *            if there utf-8 is not supported when attempting to encode url segments.
	 * @bodyparams uid,password,titleCode,firstName,lastName
	 * @security Permitted only for customer managers, clients or trusted clients.
	 */
	@Secured(
	{ "ROLE_CLIENT", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(method = RequestMethod.POST, consumes =
	{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	@SuppressWarnings("squid:S1160")
	public UserWsDTO registerUser(@RequestBody final UserSignUpWsDTO user,
			@RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields, final HttpServletRequest httpRequest,
			final HttpServletResponse httpResponse) throws DuplicateUidException, UnknownIdentifierException, //NOSONAR
			IllegalArgumentException, WebserviceValidationException, UnsupportedEncodingException //NOSONAR
	{
		validate(user, "user", userSignUpDTOValidator);
		final RegisterData registration = getDataMapper().map(user, RegisterData.class,
				"login,password,titleCode,firstName,lastName");
		mealeculeCustomerFacade.register(registration);
		final String userId = user.getUid();
		httpResponse.setHeader(YcommercewebservicesConstants.LOCATION, getAbsoluteLocationURL(httpRequest, userId));
		return getDataMapper().map(mealeculeCustomerFacade.getUserForUID(userId), UserWsDTO.class, fields);
	}

	protected String getAbsoluteLocationURL(final HttpServletRequest httpRequest, final String uid)
			throws UnsupportedEncodingException
	{
		final String requestURL = httpRequest.getRequestURL().toString();
		final StringBuilder absoluteURLSb = new StringBuilder(requestURL);
		if (!requestURL.endsWith(YcommercewebservicesConstants.SLASH))
		{
			absoluteURLSb.append(YcommercewebservicesConstants.SLASH);
		}
		absoluteURLSb.append(UriUtils.encodePathSegment(uid, StandardCharsets.UTF_8.name()));
		return absoluteURLSb.toString();
	}

	protected void registerNewUser(final String login, final String password, final String titleCode, final String firstName,
			final String lastName) throws RequestParameterException, DuplicateUidException //NOSONAR
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("registerUser: login=" + sanitize(login));
		}

		if (!EmailValidator.getInstance().isValid(login))
		{
			throw new RequestParameterException("Login [" + sanitize(login) + "] is not a valid e-mail address!",
					RequestParameterException.INVALID, "login");
		}

		final RegisterData registration = new RegisterData();
		registration.setFirstName(firstName);
		registration.setLastName(lastName);
		registration.setLogin(login);
		registration.setPassword(password);
		registration.setTitleCode(titleCode);
		mealeculeCustomerFacade.register(registration);
	}


	protected void convertToCustomer(final String password, final String guid)
			throws RequestParameterException, DuplicateUidException //NOSONAR
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("registerUser: guid=" + sanitize(guid));
		}

		try
		{
			mealeculeCustomerFacade.changeGuestToCustomer(password, guid);
		}
		catch (final UnknownIdentifierException ex)
		{
			throw new RequestParameterException("Order with guid " + sanitize(guid) + " not found in current BaseStore",
					RequestParameterException.UNKNOWN_IDENTIFIER, "guid", ex);
		}
		catch (final IllegalArgumentException ex)
		{
			// Occurs when order does not belong to guest user.
			// For security reasons it's better to treat it as "unknown identifier" error
			throw new RequestParameterException("Order with guid " + sanitize(guid) + " not found in current BaseStore",
					RequestParameterException.UNKNOWN_IDENTIFIER, "guid", ex);
		}
	}

	/**
	 * Returns customer profile.
	 *
	 * @param fields
	 *           the fields
	 * @return Customer profile.
	 * @queryparam fields Response configuration (list of fields, which should be returned in the response)
	 * @security Permitted for clients, customers and customer managers
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public UserWsDTO getUser(@RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		final CustomerData customerData = mealeculeCustomerFacade.getCurrentCustomer();
		return getDataMapper().map(customerData, UserWsDTO.class, fields);
	}

	/**
	 * Updates customer profile. Attributes not provided in the request body will be defined again (set to null or default).
	 *
	 * @formparam firstName Customer's first name.
	 * @formparam lastName Customer's last name.
	 * @formparam titleCode Customer's title code. For a list of codes, see /{baseSiteId}/titles resource
	 * @formparam language Customer's language.
	 * @formparam currency Customer's currency.
	 * @throws DuplicateUidException
	 * @security Permitted for trusted clients, customers and customer managers. Trusted client or customer manager is able
	 *           to impersonate as any other user and change profile on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void putUser(@RequestParam final String firstName, @RequestParam final String lastName,
			@RequestParam(required = true) final String titleCode, final HttpServletRequest request) throws DuplicateUidException
	{
		final CustomerData customer = mealeculeCustomerFacade.getCurrentCustomer();
		if (LOG.isDebugEnabled())
		{
			LOG.debug("putCustomer: userId=" + customer.getUid());
		}
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setTitleCode(titleCode);
		customer.setLanguage(null);
		customer.setCurrency(null);
		httpRequestCustomerDataPopulator.populate(request, customer);

		mealeculeCustomerFacade.updateFullProfile(customer);
	}

	/**
	 * Updates customer profile. Attributes not provided in the request body will be defined again (set to null or default).
	 *
	 * @param user
	 *           User's object
	 * @bodyparams firstName,lastName,titleCode,currency(isocode),language(isocode)
	 * @throws DuplicateUidException
	 * @security Permitted for trusted clients, customers and customer managers. Trusted client or customer manager is able
	 *           to impersonate as any other user and change profile on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT, consumes =
	{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public void putUser(@RequestBody final UserWsDTO user) throws DuplicateUidException
	{
		validate(user, "user", putUserDTOValidator);

		final CustomerData customer = mealeculeCustomerFacade.getCurrentCustomer();
		if (LOG.isDebugEnabled())
		{
			LOG.debug("putCustomer: userId=" + customer.getUid());
		}

		getDataMapper().map(user, customer, "firstName,lastName,titleCode,currency(isocode),language(isocode)", true);
		mealeculeCustomerFacade.updateFullProfile(customer);
	}

	/**
	 * Updates customer profile. Only attributes provided in the request body will be changed.
	 *
	 * @formparam firstName Customer's first name.
	 * @formparam lastName Customer's last name.
	 * @formparam titleCode Customer's title code. For a list of codes, see /{baseSiteId}/titles resource
	 * @formparam language Customer's language.
	 * @formparam currency Customer's currency.
	 * @throws DuplicateUidException
	 * @security Permitted for trusted clients, customers and customer managers. Trusted client or customer manager is able
	 *           to impersonate as any other user and change profile on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}", method = RequestMethod.PATCH)
	@ResponseStatus(HttpStatus.OK)
	public void updateUser(final HttpServletRequest request) throws DuplicateUidException
	{
		final CustomerData customer = mealeculeCustomerFacade.getCurrentCustomer();
		if (LOG.isDebugEnabled())
		{
			LOG.debug("updateUser: userId=" + customer.getUid());
		}
		httpRequestCustomerDataPopulator.populate(request, customer);
		mealeculeCustomerFacade.updateFullProfile(customer);
	}

	/**
	 * Updates customer profile. Only attributes provided in the request body will be changed.
	 *
	 * @param user
	 *           User's object
	 * @bodyparams firstName,lastName,titleCode,currency(isocode),language(isocode)
	 * @throws DuplicateUidException
	 * @security Permitted for trusted clients, customers and customer managers. Trusted client or customer manager is able
	 *           to impersonate as any other user and change profile on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}", method = RequestMethod.PATCH, consumes =
	{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public void updateUser(@RequestBody final UserWsDTO user) throws DuplicateUidException
	{
		final CustomerData customer = mealeculeCustomerFacade.getCurrentCustomer();
		if (LOG.isDebugEnabled())
		{
			LOG.debug("updateUser: userId=" + customer.getUid());
		}

		getDataMapper().map(user, customer, "firstName,lastName,titleCode,currency(isocode),language(isocode)", false);
		mealeculeCustomerFacade.updateFullProfile(customer);
	}

	/**
	 * Removes customer profile.
	 *
	 * @security Permitted for trusted clients, customers and customer managers. Trusted client is able to impersonate as
	 *           any other user and deactivate profile on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deactivateUser()
	{
		final UserModel userModel = userService.getCurrentUser();
		if (!userModel.isLoginDisabled())
		{
			userModel.setLoginDisabled(true);
			modelService.save(userModel);
		}
		if (LOG.isDebugEnabled())
		{
			LOG.debug("deactivateUser: userId=" + userModel.getUid());
		}
	}

	/**
	 * Changes customer's login.
	 *
	 * @formparam newLogin Customer's new login. Customer login is case insensitive.
	 * @formparam password Customer's current password.
	 * @throws DuplicateUidException
	 * @throws PasswordMismatchException
	 * @throws RequestParameterException
	 * @security Permitted for trusted clients, customers and customer managers. Trusted client or customer manager is able
	 *           to impersonate as any other user and change login on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/login", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void changeLogin(@RequestParam final String newLogin, @RequestParam final String password)
			throws DuplicateUidException, PasswordMismatchException, RequestParameterException //NOSONAR
	{
		if (!EmailValidator.getInstance().isValid(newLogin))
		{
			throw new RequestParameterException("Login [" + newLogin + "] is not a valid e-mail address!",
					RequestParameterException.INVALID, "newLogin");
		}
		mealeculeCustomerFacade.changeUid(newLogin, password);
	}

	/**
	 * Changes customer's password.
	 *
	 * @formparam new New password
	 * @formparam old Old password. Required only for ROLE_CUSTOMERGROUP
	 * @security Permitted for trusted clients, customers and customer managers. Trusted client or customer manager may
	 *           change someone's else password without knowing the old one.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/password", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void changePassword(@PathVariable final String userId, @RequestParam(required = false) final String old,
			@RequestParam(value = "new") final String newPassword)
	{
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		final UserSignUpWsDTO customer = new UserSignUpWsDTO();
		customer.setPassword(newPassword);
		validate(customer, "password", passwordStrengthValidator);
		if (containsRole(auth, "ROLE_TRUSTED_CLIENT") || containsRole(auth, "ROLE_CUSTOMERMANAGERGROUP"))
		{
			userService.setPassword(userId, newPassword);
		}
		else
		{
			if (StringUtils.isEmpty(old))
			{
				throw new RequestParameterException("Request parameter 'old' is missing.", RequestParameterException.MISSING, "old");
			}
			mealeculeCustomerFacade.changePassword(old, newPassword);
		}
	}

	protected boolean containsRole(final Authentication auth, final String role)
	{
		for (final GrantedAuthority ga : auth.getAuthorities())
		{
			if (ga.getAuthority().equals(role))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns customer's addresses.
	 *
	 * @queryparam fields Response configuration (list of fields, which should be returned in the response)
	 * @return List of customer's addresses
	 * @security Permitted for trusted clients, customers and customer managers. Trusted client or customer manager is able
	 *           to impersonate as any other user and access data on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/addresses", method = RequestMethod.GET)
	@ResponseBody
	public AddressListWsDTO getAddresses(@RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		final List<AddressData> addressList = getUserFacade().getAddressBook();
		final AddressDataList addressDataList = new AddressDataList();
		addressDataList.setAddresses(addressList);
		return getDataMapper().map(addressDataList, AddressListWsDTO.class, fields);
	}

	/**
	 * Creates a new address.
	 *
	 * @formparam firstName Customer's first name. This parameter is required.
	 * @formparam lastName Customer's last name. This parameter is required.
	 * @formparam titleCode Customer's title code. This parameter is required. For a list of codes, see /{baseSiteId}/titles
	 *            resource
	 * @formparam country.isocode Country isocode. This parameter is required and have influence on how rest of parameters
	 *            are validated (e.g. if parameters are required : line1,line2,town,postalCode,region.isocode)
	 * @formparam line1 First part of address. If this parameter is required depends on country (usually it is required).
	 * @formparam line2 Second part of address. If this parameter is required depends on country (usually it is not
	 *            required)
	 * @formparam town Town name. If this parameter is required depends on country (usually it is required)
	 * @formparam postalCode Postal code. If this parameter is required depends on country (usually it is required)
	 * @formparam region.isocode Isocode for region. If this parameter is required depends on country.
	 * @queryparam fields Response configuration (list of fields, which should be returned in the response)
	 * @return Created address
	 * @throws WebserviceValidationException
	 * @security Permitted for customers, guests, customer managers or trusted client. Trusted client or customer manager is
	 *           able to impersonate as any other user and access data on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_GUEST", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/addresses", method = RequestMethod.POST)
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public AddressWsDTO createAddress(final HttpServletRequest request,
			@RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields) throws WebserviceValidationException //NOSONAR
	{
		final AddressData addressData = super.createAddressInternal(request);
		return getDataMapper().map(addressData, AddressWsDTO.class, fields);
	}

	/**
	 * Created a new address.
	 *
	 * @param address
	 *           Address object
	 * @queryparam fields Response configuration (list of fields, which should be returned in response)
	 * @bodyparams firstName,lastName,titleCode,line1,line2,town,postalCode,country(isocode),region(isocode), defaultAddress
	 * @return Created address
	 * @throws WebserviceValidationException
	 * @security Permitted for customers, guests, customer managers or trusted client. Trusted client or customer manager is
	 *           able to impersonate as any other user and access data on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_GUEST", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/addresses", method = RequestMethod.POST, consumes =
	{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public AddressWsDTO createAddress(@RequestBody final AddressWsDTO address,
			@RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields) throws WebserviceValidationException //NOSONAR
	{
		validate(address, "address", getAddressDTOValidator());
		final AddressData addressData = getDataMapper().map(address, AddressData.class,
				"firstName,lastName,titleCode,line1,line2,town,postalCode,country(isocode),region(isocode),defaultAddress,phone");
		addressData.setShippingAddress(true);
		addressData.setVisibleInAddressBook(true);

		getUserFacade().addAddress(addressData);
		if (addressData.isDefaultAddress())
		{
			getUserFacade().setDefaultAddress(addressData);
		}

		return getDataMapper().map(addressData, AddressWsDTO.class, fields);
	}

	/**
	 * Returns detailed information about address with a given id.
	 *
	 * @queryparam fields Response configuration (list of fields, which should be returned in the response)
	 * @return Address data
	 * @throws WebserviceValidationException
	 * @security Permitted for customers, guests, customer managers or trusted client. Trusted client or customer manager is
	 *           able to impersonate as any other user and access data on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_GUEST", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/addresses/{addressId}", method = RequestMethod.GET)
	@ResponseBody
	public AddressWsDTO getAddress(@PathVariable final String addressId,
			@RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields) throws WebserviceValidationException //NOSONAR
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("getAddress: id=" + sanitize(addressId));
		}
		final AddressData addressData = getUserFacade().getAddressForCode(addressId);
		if (addressData == null)
		{
			throw new RequestParameterException(
					"Address with given id: '" + sanitize(addressId) + "' doesn't exist or belong to another user", //NOSONAR
					RequestParameterException.INVALID, "addressId");
		}

		return getDataMapper().map(addressData, AddressWsDTO.class, fields);
	}

	/**
	 * Updates the address. Attributes not provided in the request will be defined again (set to null or default).
	 *
	 * @formparam firstName Customer's first name. This parameter is required.
	 * @formparam lastName Customer's last name. This parameter is required.
	 * @formparam titleCode Customer's title code. This parameter is required. For a list of codes, see /{baseSiteId}/titles
	 *            resource
	 * @formparam country .isocode Country isocode. This parameter is required and have influence on how rest of parameters
	 *            are validated (e.g. if parameters are required : line1,line2,town,postalCode,region.isocode)
	 * @formparam line1 First part of address. If this parameter is required depends on country (usually it is required).
	 * @formparam line2 Second part of address. If this parameter is required depends on country (usually it is not
	 *            required)
	 * @formparam town Town name. If this parameter is required depends on country (usually it is required)
	 * @formparam postalCode Postal code. If this parameter is required depends on country (usually it is required)
	 *            restparam region .isocode Isocode for region. If this parameter is required depends on country.
	 * @formparam defaultAddress Parameter specifies if address should be default for customer
	 * @throws WebserviceValidationException
	 * @security Permitted for customers, guests, customer managers or trusted client. Trusted client or customer manager is
	 *           able to impersonate as any other user and access data on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_GUEST", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/addresses/{addressId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void putAddress(@PathVariable final String addressId, final HttpServletRequest request)
			throws WebserviceValidationException //NOSONAR
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("editAddress: id=" + sanitize(addressId));
		}
		final AddressData addressData = getUserFacade().getAddressForCode(addressId);
		if (addressData == null)
		{
			throw new RequestParameterException(
					"Address with given id: '" + sanitize(addressId) + "' doesn't exist or belong to another user",
					RequestParameterException.INVALID, "addressId");
		}
		final boolean isAlreadyDefaultAddress = addressData.isDefaultAddress();
		addressData.setFirstName(null);
		addressData.setLastName(null);
		addressData.setCountry(null);
		addressData.setLine1(null);
		addressData.setLine2(null);
		addressData.setPostalCode(null);
		addressData.setRegion(null);
		addressData.setTitle(null);
		addressData.setTown(null);
		addressData.setDefaultAddress(false);
		addressData.setFormattedAddress(null);

		getHttpRequestAddressDataPopulator().populate(request, addressData);

		final Errors errors = new BeanPropertyBindingResult(addressData, "addressData");
		getAddressValidator().validate(addressData, errors);

		if (errors.hasErrors())
		{
			throw new WebserviceValidationException(errors);
		}
		getUserFacade().editAddress(addressData);

		if (!isAlreadyDefaultAddress && addressData.isDefaultAddress())
		{
			getUserFacade().setDefaultAddress(addressData);
		}
	}

	/**
	 * Updates the address. Attributes not provided in the request will be defined again (set to null or default).
	 *
	 * @param address
	 *           Address object
	 * @bodyparams firstName,lastName,titleCode,line1,line2,town,postalCode,region(isocode),country(isocode), defaultAddress
	 * @throws WebserviceValidationException
	 * @security Permitted for customers, guests, customer managers or trusted client. Trusted client or customer manager is
	 *           able to impersonate as any other user and access data on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_GUEST", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/addresses/{addressId}", method = RequestMethod.PUT, consumes =
	{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public void putAddress(@PathVariable final String addressId, @RequestBody final AddressWsDTO address)
			throws WebserviceValidationException //NOSONAR
	{
		validate(address, "address", getAddressDTOValidator());
		final AddressData addressData = getUserFacade().getAddressForCode(addressId);
		if (addressData == null)
		{
			throw new RequestParameterException(
					"Address with given id: '" + sanitize(addressId) + "' doesn't exist or belong to another user",
					RequestParameterException.INVALID, "addressId");
		}
		final boolean isAlreadyDefaultAddress = addressData.isDefaultAddress();
		addressData.setFormattedAddress(null);
		getDataMapper().map(address, addressData,
				"firstName,lastName,titleCode,line1,line2,town,postalCode,region(isocode),country(isocode),defaultAddress", true);

		getUserFacade().editAddress(addressData);

		if (!isAlreadyDefaultAddress && addressData.isDefaultAddress())
		{
			getUserFacade().setDefaultAddress(addressData);
		}
	}

	/**
	 * Updates the address. Only attributes provided in the request body will be changed.
	 *
	 * @formparam firstName Customer's first name. This parameter is required.
	 * @formparam lastName Customer's last name. This parameter is required.
	 * @formparam titleCode Customer's title code. This parameter is required. For a list of codes, see /{baseSiteId}/titles
	 *            resource
	 * @formparam country.isocode Country isocode. This parameter is required and have influence on how rest of parameters
	 *            are validated (e.g. if parameters are required : line1,line2,town,postalCode,region.isocode)
	 * @formparam line1 First part of address. If this parameter is required depends on country (usually it is required).
	 * @formparam line2 Second part of address. If this parameter is required depends on country (usually it is not
	 *            required)
	 * @formparam town Town name. If this parameter is required depends on country (usually it is required)
	 * @formparam postalCode Postal code. If this parameter is required depends on country (usually it is required)
	 * @formparam region.isocode ISO code for region. If this parameter is required depends on country.
	 * @formparam defaultAddress Parameter specifies if address should be default for customer
	 * @throws WebserviceValidationException
	 * @security Permitted for customers, guests, customer managers or trusted client. Trusted client or customer manager is
	 *           able to impersonate as any other user and access data on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_GUEST", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/addresses/{addressId}", method = RequestMethod.PATCH)
	@ResponseStatus(HttpStatus.OK)
	public void patchAddress(@PathVariable final String addressId, final HttpServletRequest request)
			throws WebserviceValidationException //NOSONAR
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("editAddress: id=" + sanitize(addressId));
		}
		final AddressData addressData = getUserFacade().getAddressForCode(addressId);
		if (addressData == null)
		{
			throw new RequestParameterException(
					"Address with given id: '" + sanitize(addressId) + "' doesn't exist or belong to another user",
					RequestParameterException.INVALID, "addressId");
		}
		final boolean isAlreadyDefaultAddress = addressData.isDefaultAddress();
		addressData.setFormattedAddress(null);
		final Errors errors = new BeanPropertyBindingResult(addressData, "addressData");

		getHttpRequestAddressDataPopulator().populate(request, addressData);
		getAddressValidator().validate(addressData, errors);

		if (errors.hasErrors())
		{
			throw new WebserviceValidationException(errors);
		}

		if (addressData.getId().equals(getUserFacade().getDefaultAddress().getId()))
		{
			addressData.setDefaultAddress(true);
			addressData.setVisibleInAddressBook(true);
		}
		if (!isAlreadyDefaultAddress && addressData.isDefaultAddress())
		{
			getUserFacade().setDefaultAddress(addressData);
		}
		getUserFacade().editAddress(addressData);
	}

	/**
	 * Updates the address. Only attributes provided in the request body will be changed.
	 *
	 * @param address
	 *           address object
	 * @bodyparams firstName,lastName,titleCode,line1,line2,town,postalCode,region(isocode),country(isocode), defaultAddress
	 * @throws WebserviceValidationException
	 * @security Permitted for customers, guests, customer managers or trusted client. Trusted client or customer manager is
	 *           able to impersonate as any other user and access data on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_GUEST", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/addresses/{addressId}", method = RequestMethod.PATCH, consumes =
	{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public void patchAddress(@PathVariable final String addressId, @RequestBody final AddressWsDTO address)
			throws WebserviceValidationException //NOSONAR
	{
		final AddressData addressData = getUserFacade().getAddressForCode(addressId);
		if (addressData == null)
		{
			throw new RequestParameterException(
					"Address with given id: '" + sanitize(addressId) + "' doesn't exist or belong to another user",
					RequestParameterException.INVALID, "addressId");
		}
		final boolean isAlreadyDefaultAddress = addressData.isDefaultAddress();
		addressData.setFormattedAddress(null);

		getDataMapper().map(address, addressData,
				"firstName,lastName,titleCode,line1,line2,town,postalCode,region(isocode),country(isocode),defaultAddress", false);
		validate(addressData, "address", getAddressValidator());

		if (addressData.getId().equals(getUserFacade().getDefaultAddress().getId()))
		{
			addressData.setDefaultAddress(true);
			addressData.setVisibleInAddressBook(true);
		}
		if (!isAlreadyDefaultAddress && addressData.isDefaultAddress())
		{
			getUserFacade().setDefaultAddress(addressData);
		}
		getUserFacade().editAddress(addressData);
	}

	/**
	 * Removes customer's address.
	 *
	 * @security Permitted for customers, guests, customer managers or trusted client. Trusted client or customer manager is
	 *           able to impersonate as any other user and access data on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_GUEST", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/addresses/{addressId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteAddress(@PathVariable final String addressId)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("deleteAddress: id=" + sanitize(addressId));
		}
		final AddressData address = getUserFacade().getAddressForCode(addressId);
		if (address == null)
		{
			throw new RequestParameterException(
					"Address with given id: '" + sanitize(addressId) + "' doesn't exist or belong to another user",
					RequestParameterException.INVALID, "addressId");
		}
		getUserFacade().removeAddress(address);
	}

	/**
	 * Verifies the address.
	 *
	 * @formparam country.isocode Country isocode. This parameter is required and have influence on how rest of parameters
	 *            are validated (e.g. if parameters are required : line1,line2,town,postalCode,region.isocode)
	 * @formparam line1 First part of address. If this parameter is required depends on country (usually it is required).
	 * @formparam line2 Second part of address. If this parameter is required depends on country (usually it is not
	 *            required)
	 * @formparam town Town name. If this parameter is required depends on country (usually it is required)
	 * @formparam postalCode Postal code. If this parameter is required depends on country (usually it is required)
	 * @formparam region.isocode Isocode for region. If this parameter is required depends on country.
	 * @queryparam fields Response configuration (list of fields, which should be returned in the response)
	 * @return verification results. If address is incorrect there are information about errors and suggested addresses
	 * @security Permitted for customers, guests, customer managers or trusted client. Trusted client or customer manager is
	 *           able to impersonate as any other user and access data on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_GUEST", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/addresses/verification", method = RequestMethod.POST)
	@ResponseBody
	public AddressValidationWsDTO verifyAddress(final HttpServletRequest request,
			@RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		final AddressData addressData = new AddressData();
		final Errors errors = new BeanPropertyBindingResult(addressData, "addressData");
		AddressValidationData validationData = new AddressValidationData();

		getHttpRequestAddressDataPopulator().populate(request, addressData);
		if (isAddressValid(addressData, errors, validationData))
		{
			validationData = verifyAddresByService(addressData, errors, validationData);
		}
		return getDataMapper().map(validationData, AddressValidationWsDTO.class, fields);
	}

	/**
	 * Verifies address
	 *
	 * @param address
	 *           address object
	 * @queryparam fields Response configuration (list of fields, which should be returned in the response)
	 * @bodyparams firstName,lastName,titleCode,line1,line2,town,postalCode,country(isocode),region(isocode)
	 * @return verification results. If address is incorrect there are information about errors and suggested addresses
	 * @security Permitted for customers, guests, customer managers or trusted client. Trusted client or customer manager is
	 *           able to impersonate as any other user and access data on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_GUEST", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/addresses/verification", method = RequestMethod.POST, consumes =
	{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseBody
	public AddressValidationWsDTO verifyAddress(@RequestBody final AddressWsDTO address,
			@RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		// validation is a bit different here
		final AddressData addressData = getDataMapper().map(address, AddressData.class,
				"firstName,lastName,titleCode,line1,line2,town,postalCode,country(isocode),region(isocode)");
		final Errors errors = new BeanPropertyBindingResult(addressData, "addressData");
		AddressValidationData validationData = new AddressValidationData();

		if (isAddressValid(addressData, errors, validationData))
		{
			validationData = verifyAddresByService(addressData, errors, validationData);
		}
		return getDataMapper().map(validationData, AddressValidationWsDTO.class, fields);
	}

	/**
	 * Checks if address is valid by a validators
	 *
	 * @formparam addressData
	 * @formparam errors
	 * @formparam validationData
	 * @return true - adress is valid , false - address is invalid
	 */
	protected boolean isAddressValid(final AddressData addressData, final Errors errors,
			final AddressValidationData validationData)
	{
		getAddressValidator().validate(addressData, errors);

		if (errors.hasErrors())
		{
			validationData.setDecision(AddressVerificationDecision.REJECT.toString());
			validationData.setErrors(createResponseErrors(errors));
			return false;
		}
		return true;
	}

	/**
	 * Verifies address by commerce service
	 *
	 * @formparam addressData
	 * @formparam errors
	 * @formparam validationData
	 * @return object with verification errors and suggested addresses list
	 */
	protected AddressValidationData verifyAddresByService(final AddressData addressData, final Errors errors,
			final AddressValidationData validationData)
	{
		final AddressVerificationResult<AddressVerificationDecision> verificationDecision = addressVerificationFacade
				.verifyAddressData(addressData);
		if (verificationDecision.getErrors() != null && !verificationDecision.getErrors().isEmpty())
		{
			populateErrors(errors, verificationDecision);
			validationData.setErrors(createResponseErrors(errors));
		}

		validationData.setDecision(verificationDecision.getDecision().toString());

		if (verificationDecision.getSuggestedAddresses() != null && !verificationDecision.getSuggestedAddresses().isEmpty())
		{
			final AddressDataList addressDataList = new AddressDataList();
			addressDataList.setAddresses(verificationDecision.getSuggestedAddresses());
			validationData.setSuggestedAddressesList(addressDataList);
		}

		return validationData;
	}

	protected ErrorListWsDTO createResponseErrors(final Errors errors)
	{
		final List<ErrorWsDTO> webserviceErrorDto = new ArrayList<>();
		validationErrorConverter.convert(errors, webserviceErrorDto);
		final ErrorListWsDTO webserviceErrorList = new ErrorListWsDTO();
		webserviceErrorList.setErrors(webserviceErrorDto);
		return webserviceErrorList;
	}

	/**
	 * Populates Errors object
	 *
	 * @param errors
	 * @param addressVerificationResult
	 */
	protected void populateErrors(final Errors errors,
			final AddressVerificationResult<AddressVerificationDecision> addressVerificationResult)
	{
		addressDataErrorsPopulator.populate(addressVerificationResult, errors);
	}

	/**
	 * Return customer's credit card payment details list.
	 *
	 * @queryparam saved Type of payment details
	 * @queryparam fields Response configuration (list of fields, which should be returned in the response)
	 * @return Customer's payment details list
	 * @security Permitted for customers, customer managers or trusted client. Trusted client or customer manager is able to
	 *           impersonate as any other user and access data on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/paymentdetails", method = RequestMethod.GET)
	@ResponseBody
	public PaymentDetailsListWsDTO getPaymentInfos(@RequestParam(required = false, defaultValue = "false") final boolean saved,
			@RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("getPaymentInfos");
		}

		final CCPaymentInfoDatas paymentInfoDataList = new CCPaymentInfoDatas();
		paymentInfoDataList.setPaymentInfos(getUserFacade().getCCPaymentInfos(saved));

		return getDataMapper().map(paymentInfoDataList, PaymentDetailsListWsDTO.class, fields);
	}

	/**
	 * Returns customer's credit card payment details for a given id.
	 *
	 * @queryparam fields Response configuration (list of fields, which should be returned in the response)
	 * @return Customer's credit card payment details
	 * @security Permitted for customers, customer managers or trusted client. Trusted client or customer manager is able to
	 *           impersonate as any other user and access data on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/paymentdetails/{paymentDetailsId}", method = RequestMethod.GET)
	@ResponseBody
	public PaymentDetailsWsDTO getPaymentDetails(@PathVariable final String paymentDetailsId,
			@RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		return getDataMapper().map(getPaymentInfo(paymentDetailsId), PaymentDetailsWsDTO.class, fields);
	}

	public CCPaymentInfoData getPaymentInfo(final String paymentDetailsId)
	{
		LOG.debug("getPaymentInfo : id = " + sanitize(paymentDetailsId));
		try
		{
			final CCPaymentInfoData paymentInfoData = getUserFacade().getCCPaymentInfoForCode(paymentDetailsId);
			if (paymentInfoData == null)
			{
				throw new RequestParameterException("Payment details [" + sanitize(paymentDetailsId) + "] not found.",
						RequestParameterException.UNKNOWN_IDENTIFIER, "paymentDetailsId");
			}
			return paymentInfoData;
		}
		catch (final PKException e)
		{
			throw new RequestParameterException("Payment details [" + sanitize(paymentDetailsId) + "] not found.",
					RequestParameterException.UNKNOWN_IDENTIFIER, "paymentDetailsId", e);
		}
	}

	/**
	 * Removes customer's credit card payment details based on its ID.
	 *
	 * @security Permitted for customers, customer managers or trusted client. Trusted client or customer manager is able to
	 *           impersonate as any other user and access data on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/paymentdetails/{paymentDetailsId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deletePaymentInfo(@PathVariable final String paymentDetailsId)
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("deletePaymentInfo: id = " + sanitize(paymentDetailsId));
		}
		getPaymentInfo(paymentDetailsId);
		getUserFacade().removeCCPaymentInfo(paymentDetailsId);
	}

	/**
	 * Updates existing customer's credit card payment details based on its ID. Only attributes given in request will be
	 * changed.
	 *
	 * @formparam accountHolderName Name on card. This parameter is required.
	 * @formparam cardNumber Card number. This parameter is required.
	 * @formparam cardType Card type. This parameter is required. Call GET /{baseSiteId}/cardtypes beforehand to see what
	 *            card types are supported
	 * @formparam expiryMonth Month of expiry date. This parameter is required.
	 * @formparam expiryYear Year of expiry date. This parameter is required.
	 * @formparam issueNumber
	 * @formparam startMonth
	 * @formparam startYear
	 * @formparam subscriptionId
	 * @formparam saved Parameter defines if the payment details should be saved for the customer and than could be reused
	 *            for future orders.
	 * @formparam defaultPaymentInfo Parameter defines if the payment details should be used as default for customer.
	 * @formparam billingAddress.firstName Customer's first name. This parameter is required.
	 * @formparam billingAddress.lastName Customer's last name. This parameter is required.
	 * @formparam billingAddress.titleCode Customer's title code. This parameter is required. For a list of codes, see
	 *            /{baseSiteId}/titles resource
	 * @formparam billingAddress.country.isocode Country isocode. This parameter is required and have influence on how rest
	 *            of address parameters are validated (e.g. if parameters are required :
	 *            line1,line2,town,postalCode,region.isocode)
	 * @formparam billingAddress.line1 First part of address. If this parameter is required depends on country (usually it
	 *            is required).
	 * @formparam billingAddress.line2 Second part of address. If this parameter is required depends on country (usually it
	 *            is not required)
	 * @formparam billingAddress.town Town name. If this parameter is required depends on country (usually it is required)
	 * @formparam billingAddress.postalCode Postal code. If this parameter is required depends on country (usually it is
	 *            required)
	 * @formparam billingAddress.region.isocode Isocode for region. If this parameter is required depends on country.
	 * @throws RequestParameterException
	 * @security Permitted for customers, customer managers or trusted client. Trusted client or customer manager is able to
	 *           impersonate as any other user and access data on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/paymentdetails/{paymentDetailsId}", method = RequestMethod.PATCH)
	@ResponseStatus(HttpStatus.OK)
	public void updatePaymentInfo(@PathVariable final String paymentDetailsId, final HttpServletRequest request)
			throws RequestParameterException //NOSONAR
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("updatePaymentInfo: id = " + sanitize(paymentDetailsId));
		}

		final CCPaymentInfoData paymentInfoData = getPaymentInfo(paymentDetailsId);

		final boolean isAlreadyDefaultPaymentInfo = paymentInfoData.isDefaultPaymentInfo();
		final Collection<PaymentInfoOption> options = new ArrayList<PaymentInfoOption>();
		options.add(PaymentInfoOption.BASIC);
		options.add(PaymentInfoOption.BILLING_ADDRESS);

		getHttpRequestPaymentInfoPopulator().populate(request, paymentInfoData, options);
		validate(paymentInfoData, "paymentDetails", getCcPaymentInfoValidator());

		getUserFacade().updateCCPaymentInfo(paymentInfoData);
		if (paymentInfoData.isSaved() && !isAlreadyDefaultPaymentInfo && paymentInfoData.isDefaultPaymentInfo())
		{
			getUserFacade().setDefaultPaymentInfo(paymentInfoData);
		}
	}

	/**
	 * Updates existing customer's credit card payment details based on its ID. Only attributes given in request will be
	 * changed.
	 *
	 * @param paymentDetails
	 *           payment details object
	 * @bodyparams accountHolderName,cardNumber,cardType,issueNumber,startMonth,expiryMonth,startYear,expiryYear,
	 *             subscriptionId
	 *             ,defaultPaymentInfo,saved,billingAddress(firstName,lastName,titleCode,line1,line2,town,postalCode,
	 *             region(isocode),country(isocode),defaultAddress)
	 * @throws RequestParameterException
	 * @throws WebserviceValidationException
	 * @security Permitted for customers, customer managers or trusted client. Trusted client or customer manager is able to
	 *           impersonate as any other user and access data on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/paymentdetails/{paymentDetailsId}", method = RequestMethod.PATCH, consumes =
	{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public void updatePaymentInfo(@PathVariable final String paymentDetailsId,
			@RequestBody final PaymentDetailsWsDTO paymentDetails) throws RequestParameterException //NOSONAR
	{
		final CCPaymentInfoData paymentInfoData = getPaymentInfo(paymentDetailsId);
		final boolean isAlreadyDefaultPaymentInfo = paymentInfoData.isDefaultPaymentInfo();

		getDataMapper().map(paymentDetails, paymentInfoData,
				"accountHolderName,cardNumber,cardType,issueNumber,startMonth,expiryMonth,startYear,expiryYear,subscriptionId,defaultPaymentInfo,saved,billingAddress(firstName,lastName,titleCode,line1,line2,town,postalCode,region(isocode),country(isocode),defaultAddress)",
				false);
		validate(paymentInfoData, "paymentDetails", getCcPaymentInfoValidator());

		getUserFacade().updateCCPaymentInfo(paymentInfoData);
		if (paymentInfoData.isSaved() && !isAlreadyDefaultPaymentInfo && paymentInfoData.isDefaultPaymentInfo())
		{
			getUserFacade().setDefaultPaymentInfo(paymentInfoData);
		}

	}

	/**
	 * Updates existing customer's credit card payment info based on the payment info ID. Attributes not given in request
	 * will be defined again (set to null or default).
	 *
	 * @formparam accountHolderName Name on card. This parameter is required.
	 * @formparam cardNumber Card number. This parameter is required.
	 * @formparam cardType Card type. This parameter is required. Call GET /{baseSiteId}/cardtypes beforehand to see what
	 *            card types are supported
	 * @formparam expiryMonth Month of expiry date. This parameter is required.
	 * @formparam expiryYear Year of expiry date. This parameter is required.
	 * @formparam issueNumber
	 * @formparam startMonth
	 * @formparam startYear
	 * @formparam subscriptionId
	 * @formparam saved Parameter defines if the payment details should be saved for the customer and than could be reused
	 *            for future orders.
	 * @formparam defaultPaymentInfo Parameter defines if the payment details should be used as default for customer.
	 * @formparam billingAddress.firstName Customer's first name. This parameter is required.
	 * @formparam billingAddress.lastName Customer's last name. This parameter is required.
	 * @formparam billingAddress.titleCode Customer's title code. This parameter is required. For a list of codes, see
	 *            /{baseSiteId}/titles resource
	 * @formparam billingAddress.country.isocode Country isocode. This parameter is required and have influence on how rest
	 *            of address parameters are validated (e.g. if parameters are required :
	 *            line1,line2,town,postalCode,region.isocode)
	 * @formparam billingAddress.line1 First part of address. If this parameter is required depends on country (usually it
	 *            is required).
	 * @formparam billingAddress.line2 Second part of address. If this parameter is required depends on country (usually it
	 *            is not required)
	 * @formparam billingAddress.town Town name. If this parameter is required depends on country (usually it is required)
	 * @formparam billingAddress.postalCode Postal code. If this parameter is required depends on country (usually it is
	 *            required)
	 * @formparam billingAddress.region.isocode Isocode for region. If this parameter is required depends on country.
	 * @throws RequestParameterException
	 * @security Permitted for customers, customer managers or trusted client. Trusted client or customer manager is able to
	 *           impersonate as any other user and access data on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/paymentdetails/{paymentDetailsId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void putPaymentInfo(@PathVariable final String paymentDetailsId, final HttpServletRequest request)
			throws RequestParameterException //NOSONAR
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("updatePaymentInfo: id = " + sanitize(paymentDetailsId));
		}

		final CCPaymentInfoData paymentInfoData = getPaymentInfo(paymentDetailsId);

		final boolean isAlreadyDefaultPaymentInfo = paymentInfoData.isDefaultPaymentInfo();
		paymentInfoData.setAccountHolderName(null);
		paymentInfoData.setCardNumber(null);
		paymentInfoData.setCardType(null);
		paymentInfoData.setExpiryMonth(null);
		paymentInfoData.setExpiryYear(null);
		paymentInfoData.setDefaultPaymentInfo(false);
		paymentInfoData.setSaved(false);

		paymentInfoData.setIssueNumber(null);
		paymentInfoData.setStartMonth(null);
		paymentInfoData.setStartYear(null);
		paymentInfoData.setSubscriptionId(null);

		final AddressData address = paymentInfoData.getBillingAddress();
		address.setFirstName(null);
		address.setLastName(null);
		address.setCountry(null);
		address.setLine1(null);
		address.setLine2(null);
		address.setPostalCode(null);
		address.setRegion(null);
		address.setTitle(null);
		address.setTown(null);

		final Collection<PaymentInfoOption> options = new ArrayList<PaymentInfoOption>();
		options.add(PaymentInfoOption.BASIC);
		options.add(PaymentInfoOption.BILLING_ADDRESS);

		getHttpRequestPaymentInfoPopulator().populate(request, paymentInfoData, options);
		validate(paymentInfoData, "paymentDetails", getCcPaymentInfoValidator());

		getUserFacade().updateCCPaymentInfo(paymentInfoData);
		if (paymentInfoData.isSaved() && !isAlreadyDefaultPaymentInfo && paymentInfoData.isDefaultPaymentInfo())
		{
			getUserFacade().setDefaultPaymentInfo(paymentInfoData);
		}
	}

	/**
	 * Updates existing customer's credit card payment info based on the payment info ID. Attributes not given in request
	 * will be defined again (set to null or default).
	 *
	 * @param paymentDetails
	 *           payment details object
	 * @bodyparams accountHolderName,cardNumber,cardType,issueNumber,startMonth,expiryMonth,startYear,expiryYear,
	 *             subscriptionId
	 *             ,defaultPaymentInfo,saved,billingAddress(firstName,lastName,titleCode,line1,line2,town,postalCode,
	 *             region(isocode),country(isocode),defaultAddress)
	 * @throws RequestParameterException
	 * @security Permitted for customers, customer managers or trusted client. Trusted client or customer manager is able to
	 *           impersonate as any other user and access data on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/paymentdetails/{paymentDetailsId}", method = RequestMethod.PUT, consumes =
	{ MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ResponseStatus(HttpStatus.OK)
	public void putPaymentInfo(@PathVariable final String paymentDetailsId, @RequestBody final PaymentDetailsWsDTO paymentDetails)
			throws RequestParameterException //NOSONAR
	{
		final CCPaymentInfoData paymentInfoData = getPaymentInfo(paymentDetailsId);
		final boolean isAlreadyDefaultPaymentInfo = paymentInfoData.isDefaultPaymentInfo();

		validate(paymentDetails, "paymentDetails", getPaymentDetailsDTOValidator());
		getDataMapper().map(paymentDetails, paymentInfoData,
				"accountHolderName,cardNumber,cardType,issueNumber,startMonth,expiryMonth,startYear,expiryYear,subscriptionId,defaultPaymentInfo,saved,billingAddress(firstName,lastName,titleCode,line1,line2,town,postalCode,region(isocode),country(isocode),defaultAddress)",
				true);

		getUserFacade().updateCCPaymentInfo(paymentInfoData);
		if (paymentInfoData.isSaved() && !isAlreadyDefaultPaymentInfo && paymentInfoData.isDefaultPaymentInfo())
		{
			getUserFacade().setDefaultPaymentInfo(paymentInfoData);
		}
	}

	/**
	 * Returns all customer groups of a customer.
	 *
	 * @queryparam fields Response configuration (list of fields, which should be returned in the response)
	 * @return All customer groups of a customer.
	 * @security Permitted for customers, customer managers or trusted client. Trusted client or customer manager is able to
	 *           impersonate as any other user and access data on their behalf.
	 */
	@Secured(
	{ "ROLE_CUSTOMERGROUP", "ROLE_TRUSTED_CLIENT", "ROLE_CUSTOMERMANAGERGROUP" })
	@RequestMapping(value = "/{userId}/customergroups", method = RequestMethod.GET)
	@ResponseBody
	public UserGroupListWsDTO getAllCustomerGroupsForCustomer(@PathVariable final String userId,
			@RequestParam(defaultValue = DEFAULT_FIELD_SET) final String fields)
	{
		final UserGroupDataList userGroupDataList = new UserGroupDataList();
		userGroupDataList.setUserGroups(customerGroupFacade.getCustomerGroupsForUser(userId));
		return getDataMapper().map(userGroupDataList, UserGroupListWsDTO.class, fields);
	}

	protected Set<OrderStatus> extractOrderStatuses(final String statuses)
	{
		final String[] statusesStrings = statuses.split(YcommercewebservicesConstants.OPTIONS_SEPARATOR);

		final Set<OrderStatus> statusesEnum = new HashSet<>();
		for (final String status : statusesStrings)
		{
			statusesEnum.add(OrderStatus.valueOf(status));
		}
		return statusesEnum;
	}

	protected OrderHistoriesData createOrderHistoriesData(final SearchPageData<OrderHistoryData> result)
	{
		final OrderHistoriesData orderHistoriesData = new OrderHistoriesData();

		orderHistoriesData.setOrders(result.getResults());
		orderHistoriesData.setSorts(result.getSorts());
		orderHistoriesData.setPagination(result.getPagination());

		return orderHistoriesData;
	}


	@RequestMapping(value = "/{userId}/preferredMealecules", method = RequestMethod.POST)
	@ResponseBody
	public PreferredMealeculeWsDTO createPreferredMealecule(@RequestParam(defaultValue = DEFAULT_FIELD_SET)
	final String fields, @RequestParam (defaultValue = StringUtils.EMPTY) final String preferredMealecule, @RequestParam (defaultValue = StringUtils.EMPTY) final String minMealecule, @RequestParam (defaultValue = StringUtils.EMPTY) final String maxMealecule, final HttpServletRequest httpRequest, final HttpServletResponse httpResponse)
	{
		final UserModel userModel = userService.getCurrentUser();
		if(null != userModel){
			final CustomerData userData = getCustomerData(userModel);

			final PreferredMealeculeData mealeculeData = mealeculeCustomerFacade.updatePreferredMealecule(preferredMealecule, maxMealecule, minMealecule, userModel, userData);
			return getDataMapper().map(mealeculeData, PreferredMealeculeWsDTO.class, fields);
		}
		return null;
	}


	@RequestMapping(value = "/{userId}/preferredMealecules", method = RequestMethod.GET)
	@ResponseBody
	public PreferredMealeculeWsDTO getPreferredMealecule(@RequestParam(defaultValue = DEFAULT_FIELD_SET)
	final String fields)
	{
		final UserModel userModel = userService.getCurrentUser();
		if(null != userModel) {
			return getPreferredMealeculeWsDTO(fields, userModel);
		}
		return null;
	}

	@RequestMapping(value = "/{userId}/gameData", method = RequestMethod.POST)
	@ResponseBody
	public CustomerGameWsDTO createOrUpdateUserGameData(@RequestParam(defaultValue = DEFAULT_FIELD_SET)
															final String fields, @RequestParam (defaultValue = "0") final Integer coins, final HttpServletRequest httpRequest, final HttpServletResponse httpResponse)
	{
		final UserModel userModel = userService.getCurrentUser();
		if(null != userModel){
			final CustomerGameData customerGameData = mealeculeCustomerFacade.updateCustomerGameData(coins, null, null, null,
					userModel);
			final CustomerData userData = getCustomerData(userModel);
			if (null != userData.getGameData())
			{
				return getCustomerGameWsDTO(fields, userData, customerGameData);
			}
		}
		return null;
	}

	@RequestMapping(value = "/{userId}/customerPersonalData", method = RequestMethod.POST)
	@ResponseBody
	public CustomerGameWsDTO createOrUpdateUserPersonalData(@RequestParam(defaultValue = DEFAULT_FIELD_SET)
	final String fields,final Double height, final Double weight, final Integer age, final HttpServletRequest httpRequest, final HttpServletResponse httpResponse)
	{
		final UserModel userModel = userService.getCurrentUser();
		if (null != userModel)
		{
			final CustomerGameData customerGameData = mealeculeCustomerFacade.updateCustomerGameData(null, height, weight, age,
					userModel);
			final CustomerData userData = getCustomerData(userModel);
			if (null != userData.getGameData())
			{
				return getCustomerGameWsDTO(fields, userData, customerGameData);
			}
		}
		return null;
	}


	@RequestMapping(value = "/{userId}/gameData", method = RequestMethod.GET)
	@ResponseBody
	public CustomerGameWsDTO getUserGameData(@RequestParam(defaultValue = DEFAULT_FIELD_SET)
														 final String fields)
	{
		final UserModel userModel = userService.getCurrentUser();
		if(null != userModel) {
			final CustomerData userData = getCustomerData(userModel);
			final CustomerGameData customerGameData = new CustomerGameData();
			if(null != userData.getGameData()) {
				return getCustomerGameWsDTO(fields, userData, customerGameData);
			}
		}
		return null;
	}

	private CustomerGameWsDTO getCustomerGameWsDTO(final String fields, final CustomerData userData, final CustomerGameData customerGameData) {
		customerGameData.setCoins(userData.getGameData().getCoins());
		customerGameData.setBadge(userData.getGameData().getBadge());
		return getDataMapper().map(customerGameData, CustomerGameWsDTO.class, fields);
	}

	private PreferredMealeculeWsDTO getPreferredMealeculeWsDTO(final String fields, final UserModel userModel) {
		final CustomerData userData = getCustomerData(userModel);
		final PreferredMealeculeData mealeculeData = new PreferredMealeculeData();
		mealeculeData.setPreferredMealecule(userData.getPreferredMealecule());
		mealeculeData.setMaxMealecule(userData.getMaxMealecule());
		mealeculeData.setMinMealecule(userData.getMinMealecule());
		return getDataMapper().map(mealeculeData, PreferredMealeculeWsDTO.class, fields);
	}

	private CustomerData getCustomerData(final UserModel userModel) {
		final CustomerData userData = new CustomerData();
		customerConverter.convert(userModel, userData);
		return userData;
	}


}
