/**
 *
 */
package com.mealecule.facades.populators;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.mealecule.core.enums.LevelEnum;
import com.mealecule.core.enums.StatusEnum;
import com.mealecule.core.model.BadgeModel;
import com.mealecule.core.user.data.CustomerGameData;


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
		final CustomerGameData customerGameData = source.getGameData();

		Integer intCoins = 0;
		final boolean isCustomerGameDataNotNull = null != customerGameData ? Boolean.TRUE : Boolean.FALSE;
		if (isCustomerGameDataNotNull && null != customerGameData.getCoins())
		{
			target.setCoins(customerGameData.getCoins());
			intCoins = customerGameData.getCoins();
		}
		else if (null != target.getCoins())
		{
			target.setCoins(target.getCoins());
			intCoins = target.getCoins();
		}
		if(isCustomerGameDataNotNull) {
			if (null != customerGameData.getHeight()) {
				target.setHeight(customerGameData.getHeight());
			}
			if (null != customerGameData.getWeight()) {
				target.setWeight(customerGameData.getWeight());
			}
			if (null != customerGameData.getAge()) {
				target.setAge(customerGameData.getAge());
			}
		}
		final boolean existingSilverActiveBadge = null != badgeModel.getLevel() && null != badgeModel.getStatus() && StatusEnum.ACTIVE.equals(badgeModel.getStatus()) && LevelEnum.SILVER.equals(badgeModel.getLevel());
		final boolean existingBronzeActiveBadge = null != badgeModel.getLevel() && null != badgeModel.getStatus() && StatusEnum.ACTIVE.equals(badgeModel.getStatus()) && LevelEnum.BRONZE.equals(badgeModel.getLevel());

		if (intCoins != null)
		{
			if (intCoins >= 11000)
			{
				if (existingSilverActiveBadge || existingBronzeActiveBadge)
				{
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
			else if (intCoins >= 1100)
			{
				if (existingBronzeActiveBadge)
				{
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
			else if (intCoins >= 100 && !existingBronzeActiveBadge)
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
	}


	private BadgeModel getBadgeModel(BadgeModel badgeModel) {
		try {
			LOG.debug("Deleting badge model: " + badgeModel.getId());
			getModelService().remove(badgeModel);
			LOG.debug("Succcessfully deleted associated appointment model");
		} catch (final Exception e) {
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
	public void setModelService(final ModelService modelService) {
		this.modelService = modelService;
	}
}
