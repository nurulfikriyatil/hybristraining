/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.constants.WebConstants;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.ContentPageModel;

import de.hybris.platform.util.Config;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.training.core.book.dao.BookDao;
import org.training.facades.book.BookFacade;
import org.training.facades.book.data.BookData;
import org.training.facades.city.CityFacade;
import org.training.facades.city.data.CityData;
import org.training.facades.district.DistrictFacade;
import org.training.facades.district.data.DistrictData;
import org.training.facades.province.ProvinceFacade;
import org.training.facades.province.data.ProvinceData;
import org.training.facades.student.StudentFacade;
import org.training.facades.student.data.StudentData;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Controller for home page
 */
@Controller
@RequestMapping("/")
public class HomePageController extends AbstractPageController
{
	private static final String LOGOUT = "logout";
	private static final String ACCOUNT_CONFIRMATION_SIGNOUT_TITLE = "account.confirmation.signout.title";
	private static final String ACCOUNT_CONFIRMATION_CLOSE_TITLE = "account.confirmation.close.title";

	@Resource(name = "defaultStudentFacade")
	private StudentFacade studentFacade;

	@Resource(name = "defaultBookFacade")
	private BookFacade bookFacade;

	@Resource(name = "defaultProvinceFacade")
	private ProvinceFacade provinceFacade;

	@Resource(name = "defaultCityFacade")
	private CityFacade cityFacade;

	@Resource(name = "defaultDistrictFacade")
	private DistrictFacade districtFacade;

	@RequestMapping(method = RequestMethod.GET)
	public String home(@RequestParam(value = WebConstants.CLOSE_ACCOUNT, defaultValue = "false") final boolean closeAcc,
			@RequestParam(value = LOGOUT, defaultValue = "false") final boolean logout, final Model model,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		if (logout)
		{
			String message = ACCOUNT_CONFIRMATION_SIGNOUT_TITLE;
			if (closeAcc)
			{
				message = ACCOUNT_CONFIRMATION_CLOSE_TITLE;
			}
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.INFO_MESSAGES_HOLDER, message);
			return REDIRECT_PREFIX + ROOT;
		}
		final ContentPageModel contentPage = getContentPageForLabelOrId(null);
		storeCmsPageInModel(model, contentPage);
		setUpMetaDataForContentPage(model, contentPage);
		updatePageTitle(model, contentPage);
		model.addAttribute("studentList", studentFacade.getAllStudent());
		model.addAttribute("bookList", bookFacade.getAllBook());
		model.addAttribute("provinceList", provinceFacade.getAllProvince());
		model.addAttribute("citiesList", cityFacade.getAllCity());
		model.addAttribute("districtsList", districtFacade.getAllDistrict());

		return getViewForPage(model);
	}

	@GetMapping("/student")
	public ResponseEntity<StudentData> getStudent (@RequestParam(value = "id", required = false) final String id,
												   @RequestParam(value = "name", required = false) final String name){
		if(StringUtils.isNotEmpty(id)){
			StudentData studentData = studentFacade.getStudentById(id);
			return  new ResponseEntity<>(studentData, HttpStatus.OK);
		}
		else {
			StudentData studentData = studentFacade.getStudentByName(name);
			return new ResponseEntity<>(studentData, HttpStatus.OK);
		}
	}

	@GetMapping("/book")
	public ResponseEntity<BookData> getBook (@RequestParam(value = "id", required = false) final String id,
											 @RequestParam(value = "title", required = false) final String title){
		if(StringUtils.isNotEmpty(id)){
			BookData bookData = bookFacade.getBookId(id);
			return new ResponseEntity<>(bookData, HttpStatus.OK);
		}
		else {
			BookData bookData = bookFacade.getBookByTitle(title);
			return new ResponseEntity<>(bookData, HttpStatus.OK);
		}
	}

	private static String getDeviceType(final HttpServletRequest request) {
		String userAgent = request.getHeader("user-Agent");
		if(userAgent.contains("mobile")){
			return "mobile";
		} else if (!userAgent.contains("mobile") && userAgent.contains("ipad") || userAgent.contains("andorid") || userAgent.contains("tablet"))
		{
			return "tablet";
		}
		return "desktop";
	}
	 private static String getImageResizeString (final String deviceType, String imageType){
		if(Config.getBoolean("image.resize.enable", false))
		{
			String imageUrl = Config.getString("image.resize.url", StringUtils.EMPTY);
			String imageUrlPostfix = Config.getString("image.resize.postfix", StringUtils.EMPTY);
			return  imageUrl = Config.getString("image.resizer" + deviceType + "." + imageType, "1140") + imageUrlPostfix;
		}
		else {
			return StringUtils.EMPTY;
		}
	 }

	@GetMapping("/province")
	public ResponseEntity<ProvinceData> getProvince (@RequestParam(value = "code", required = false) final String code,
													@RequestParam(value = "name", required = false) final String name){
		if(StringUtils.isNotEmpty(code)){
			ProvinceData provinceData = provinceFacade.getProvinceByCode(code);
			return new ResponseEntity<>(provinceData, HttpStatus.OK);
		}
		else {
			ProvinceData provinceData = provinceFacade.getProvinceByName(name);
			return new ResponseEntity<>(provinceData, HttpStatus.OK);
		}
	}

	@GetMapping("/city")
	public ResponseEntity<CityData> getCity (@RequestParam(value = "code", required = false) final String code,
											 @RequestParam(value = "name", required = false) final String name){
		if(StringUtils.isNotEmpty(code)){
			CityData cityData  = cityFacade.getCityByCode(code);
			return new ResponseEntity<>(cityData, HttpStatus.OK);
		}
		else {
			CityData cityData  = cityFacade.getCityByCode(name);
			return new ResponseEntity<>(cityData, HttpStatus.OK);
		}
	}

	@GetMapping("/district")
	public ResponseEntity<DistrictData> getDistrict (@RequestParam(value = "code", required = false) final String code,
													 @RequestParam(value = "name", required = false) final String name){
		if(StringUtils.isNotEmpty(code)){
			DistrictData districtData = districtFacade.getDistrictByCode(code);
			return new ResponseEntity<>(districtData, HttpStatus.OK);
		}
		else {
			DistrictData districtData = districtFacade.getDistrictByName(name);
			return new ResponseEntity<>(districtData, HttpStatus.OK);
		}
	}
	@GetMapping(value = "/district-byprovince")
	public ResponseEntity <List<DistrictData>> getDistrictByProvince(@RequestParam(value = "code", required = false) final String code,
																	 @RequestParam(value = "name", required = false) final String name){

		List<DistrictData> districtData = districtFacade.getDistrictByProvinceCode(code);
		if (CollectionUtils.isNotEmpty(districtData)) {
			return new ResponseEntity<>(districtData, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	@GetMapping(value = "/district-bycity")
	public ResponseEntity <List<DistrictData>> getDistrictByCity(@RequestParam(value = "code", required = false) final String code,
																 @RequestParam(value = "name", required = false) final String name){

		List<DistrictData> districtData = districtFacade.getDistrictByCityCode(code);
		if (CollectionUtils.isNotEmpty(districtData)) {
			return new ResponseEntity<>(districtData, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}


	protected void updatePageTitle(final Model model, final AbstractPageModel cmsPage)
	{
		storeContentPageTitleInModel(model, getPageTitleResolver().resolveHomePageTitle(cmsPage.getTitle()));
	}
}
