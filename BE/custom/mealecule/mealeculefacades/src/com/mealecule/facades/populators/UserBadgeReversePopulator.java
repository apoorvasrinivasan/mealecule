/**
 *
 */
package com.mealecule.facades.populators;

import com.mealecule.core.enums.StatusEnum;
import com.mealecule.facades.customer.impl.DefaultMealeculeCustomerFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.mealecule.core.enums.DisplayNameEnum;
import com.mealecule.core.enums.LevelEnum;
import com.mealecule.core.model.BadgeModel;
import com.mealecule.core.user.data.BadgeData;
import de.hybris.platform.servicelayer.model.ModelService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.Resource;


/**
 * @author prirawat5
 *
 */
public class UserBadgeReversePopulator implements Populator<CustomerData, UserModel>
{

	private static final Logger LOG = Logger.getLogger(UserBadgeReversePopulator.class);

	@Resource
	private ModelService modelService;

	@Override
	public void populate(final CustomerData source, final UserModel target) throws ConversionException
	{
		BadgeModel badgeModel = target.getBadge() == null ? new BadgeModel() : target.getBadge();
		final Integer intCoins = source.getGameData().getCoins();
		target.setCoins(intCoins);
		boolean existingSilverActiveBadge = null != badgeModel.getLevel() && null != badgeModel.getStatus() && StatusEnum.ACTIVE.equals(badgeModel.getStatus()) && LevelEnum.SILVER.equals(badgeModel.getLevel());
		boolean existingBronzeActiveBadge = null != badgeModel.getLevel() && null != badgeModel.getStatus() && StatusEnum.ACTIVE.equals(badgeModel.getStatus()) && LevelEnum.BRONZE.equals(badgeModel.getLevel());
		if (intCoins >= 10000)
		{
			if(existingSilverActiveBadge || existingBronzeActiveBadge) {
				badgeModel = getBadgeModel(badgeModel);
			}
			badgeModel.setLevel(LevelEnum.GOLD);

			if (badgeModel.getLevel() != null && StringUtils.isEmpty(badgeModel.getId()))
			{
				badgeModel.setId(target.getUid() + "_" + badgeModel.getLevel().getCode());
				badgeModel.setStatus(StatusEnum.ACTIVE);
			}
			getModelService().save(badgeModel);
			target.setBadge(badgeModel);
		}
		else if (intCoins >= 1000)
		{
			if(existingBronzeActiveBadge) {
				badgeModel = getBadgeModel(badgeModel);
			}
			badgeModel.setLevel(LevelEnum.SILVER);

			if (badgeModel.getLevel() != null && StringUtils.isEmpty(badgeModel.getId()))
			{
				badgeModel.setId(target.getUid() + "_" + badgeModel.getLevel().getCode());
				badgeModel.setStatus(StatusEnum.ACTIVE);
			}
			getModelService().save(badgeModel);
			target.setBadge(badgeModel);
		}
		else if (intCoins >= 200 && !existingBronzeActiveBadge)
		{
			badgeModel.setLevel(LevelEnum.BRONZE);

			if (badgeModel.getLevel() != null && StringUtils.isEmpty(badgeModel.getId()))
			{
				badgeModel.setId(target.getUid() + "_" + badgeModel.getLevel().getCode());
				badgeModel.setStatus(StatusEnum.ACTIVE);
			}
			getModelService().save(badgeModel);
			target.setBadge(badgeModel);
		}

		getModelService().save(target);
	}

	private BadgeModel getBadgeModel(BadgeModel badgeModel) {
		try {
			LOG.debug("Deleting badge model: " + badgeModel.getId());
			getModelService().remove(badgeModel);
			LOG.debug("Succcessfully deleted associated appointment model");
		} catch (Exception e) {
			LOG.debug("Error deleting badge model!", e);
			LOG.error(e);
		}
		badgeModel = getModelService().create(BadgeModel.class);
		return badgeModel;
	}

	public ModelService getModelService() {
		return modelService;
	}

	@Required
	public void setModelService(ModelService modelService) {
		this.modelService = modelService;
	}
}
