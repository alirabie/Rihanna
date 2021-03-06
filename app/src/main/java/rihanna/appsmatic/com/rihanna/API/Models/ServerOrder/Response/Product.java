package rihanna.appsmatic.com.rihanna.API.Models.ServerOrder.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Eng Ali on 1/1/2018.
 */
public class Product {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("visible_individually")
    @Expose
    private Boolean visibleIndividually;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("short_description")
    @Expose
    private Object shortDescription;
    @SerializedName("full_description")
    @Expose
    private Object fullDescription;
    @SerializedName("show_on_home_page")
    @Expose
    private Boolean showOnHomePage;
    @SerializedName("meta_keywords")
    @Expose
    private Object metaKeywords;
    @SerializedName("meta_description")
    @Expose
    private Object metaDescription;
    @SerializedName("meta_title")
    @Expose
    private Object metaTitle;
    @SerializedName("allow_customer_reviews")
    @Expose
    private Boolean allowCustomerReviews;
    @SerializedName("approved_rating_sum")
    @Expose
    private Integer approvedRatingSum;
    @SerializedName("not_approved_rating_sum")
    @Expose
    private Integer notApprovedRatingSum;
    @SerializedName("approved_total_reviews")
    @Expose
    private Integer approvedTotalReviews;
    @SerializedName("not_approved_total_reviews")
    @Expose
    private Integer notApprovedTotalReviews;
    @SerializedName("sku")
    @Expose
    private Object sku;
    @SerializedName("manufacturer_part_number")
    @Expose
    private Object manufacturerPartNumber;
    @SerializedName("gtin")
    @Expose
    private Object gtin;
    @SerializedName("is_gift_card")
    @Expose
    private Boolean isGiftCard;
    @SerializedName("require_other_products")
    @Expose
    private Boolean requireOtherProducts;
    @SerializedName("automatically_add_required_products")
    @Expose
    private Boolean automaticallyAddRequiredProducts;
    @SerializedName("is_download")
    @Expose
    private Boolean isDownload;
    @SerializedName("unlimited_downloads")
    @Expose
    private Boolean unlimitedDownloads;
    @SerializedName("max_number_of_downloads")
    @Expose
    private Integer maxNumberOfDownloads;
    @SerializedName("download_expiration_days")
    @Expose
    private Object downloadExpirationDays;
    @SerializedName("has_sample_download")
    @Expose
    private Boolean hasSampleDownload;
    @SerializedName("has_user_agreement")
    @Expose
    private Boolean hasUserAgreement;
    @SerializedName("is_recurring")
    @Expose
    private Boolean isRecurring;
    @SerializedName("recurring_cycle_length")
    @Expose
    private Integer recurringCycleLength;
    @SerializedName("recurring_total_cycles")
    @Expose
    private Integer recurringTotalCycles;
    @SerializedName("is_rental")
    @Expose
    private Boolean isRental;
    @SerializedName("rental_price_length")
    @Expose
    private Integer rentalPriceLength;
    @SerializedName("is_ship_enabled")
    @Expose
    private Boolean isShipEnabled;
    @SerializedName("is_free_shipping")
    @Expose
    private Boolean isFreeShipping;
    @SerializedName("ship_separately")
    @Expose
    private Boolean shipSeparately;
    @SerializedName("additional_shipping_charge")
    @Expose
    private Integer additionalShippingCharge;
    @SerializedName("is_tax_exempt")
    @Expose
    private Boolean isTaxExempt;
    @SerializedName("is_telecommunications_or_broadcasting_or_electronic_services")
    @Expose
    private Boolean isTelecommunicationsOrBroadcastingOrElectronicServices;
    @SerializedName("use_multiple_warehouses")
    @Expose
    private Boolean useMultipleWarehouses;
    @SerializedName("stock_quantity")
    @Expose
    private Integer stockQuantity;
    @SerializedName("display_stock_availability")
    @Expose
    private Boolean displayStockAvailability;
    @SerializedName("display_stock_quantity")
    @Expose
    private Boolean displayStockQuantity;
    @SerializedName("min_stock_quantity")
    @Expose
    private Integer minStockQuantity;
    @SerializedName("notify_admin_for_quantity_below")
    @Expose
    private Integer notifyAdminForQuantityBelow;
    @SerializedName("allow_back_in_stock_subscriptions")
    @Expose
    private Boolean allowBackInStockSubscriptions;
    @SerializedName("order_minimum_quantity")
    @Expose
    private Integer orderMinimumQuantity;
    @SerializedName("order_maximum_quantity")
    @Expose
    private Integer orderMaximumQuantity;
    @SerializedName("allowed_quantities")
    @Expose
    private Object allowedQuantities;
    @SerializedName("allow_adding_only_existing_attribute_combinations")
    @Expose
    private Boolean allowAddingOnlyExistingAttributeCombinations;
    @SerializedName("disable_buy_button")
    @Expose
    private Boolean disableBuyButton;
    @SerializedName("disable_wishlist_button")
    @Expose
    private Boolean disableWishlistButton;
    @SerializedName("available_for_pre_order")
    @Expose
    private Boolean availableForPreOrder;
    @SerializedName("pre_order_availability_start_date_time_utc")
    @Expose
    private Object preOrderAvailabilityStartDateTimeUtc;
    @SerializedName("call_for_price")
    @Expose
    private Boolean callForPrice;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("old_price")
    @Expose
    private Integer oldPrice;
    @SerializedName("product_cost")
    @Expose
    private Integer productCost;
    @SerializedName("special_price")
    @Expose
    private Object specialPrice;
    @SerializedName("special_price_start_date_time_utc")
    @Expose
    private Object specialPriceStartDateTimeUtc;
    @SerializedName("special_price_end_date_time_utc")
    @Expose
    private Object specialPriceEndDateTimeUtc;
    @SerializedName("customer_enters_price")
    @Expose
    private Boolean customerEntersPrice;
    @SerializedName("minimum_customer_entered_price")
    @Expose
    private Integer minimumCustomerEnteredPrice;
    @SerializedName("maximum_customer_entered_price")
    @Expose
    private Integer maximumCustomerEnteredPrice;
    @SerializedName("baseprice_enabled")
    @Expose
    private Boolean basepriceEnabled;
    @SerializedName("baseprice_amount")
    @Expose
    private Integer basepriceAmount;
    @SerializedName("baseprice_base_amount")
    @Expose
    private Integer basepriceBaseAmount;
    @SerializedName("has_tier_prices")
    @Expose
    private Boolean hasTierPrices;
    @SerializedName("has_discounts_applied")
    @Expose
    private Boolean hasDiscountsApplied;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("length")
    @Expose
    private Integer length;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("available_start_date_time_utc")
    @Expose
    private Object availableStartDateTimeUtc;
    @SerializedName("available_end_date_time_utc")
    @Expose
    private Object availableEndDateTimeUtc;
    @SerializedName("display_order")
    @Expose
    private Integer displayOrder;
    @SerializedName("published")
    @Expose
    private Boolean published;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;
    @SerializedName("created_on_utc")
    @Expose
    private String createdOnUtc;
    @SerializedName("updated_on_utc")
    @Expose
    private String updatedOnUtc;
    @SerializedName("product_type")
    @Expose
    private String productType;
    @SerializedName("parent_grouped_product_id")
    @Expose
    private Integer parentGroupedProductId;
    @SerializedName("role_ids")
    @Expose
    private Object roleIds;
    @SerializedName("discount_ids")
    @Expose
    private Object discountIds;
    @SerializedName("store_ids")
    @Expose
    private Object storeIds;
    @SerializedName("manufacturer_ids")
    @Expose
    private Object manufacturerIds;
    @SerializedName("images")
    @Expose
    private Object images;
    @SerializedName("attributes")
    @Expose
    private Object attributes;
    @SerializedName("associated_product_ids")
    @Expose
    private Object associatedProductIds;
    @SerializedName("tags")
    @Expose
    private List<Object> tags = null;
    @SerializedName("vendor_id")
    @Expose
    private Integer vendorId;
    @SerializedName("se_name")
    @Expose
    private Object seName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getVisibleIndividually() {
        return visibleIndividually;
    }

    public void setVisibleIndividually(Boolean visibleIndividually) {
        this.visibleIndividually = visibleIndividually;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(Object shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Object getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(Object fullDescription) {
        this.fullDescription = fullDescription;
    }

    public Boolean getShowOnHomePage() {
        return showOnHomePage;
    }

    public void setShowOnHomePage(Boolean showOnHomePage) {
        this.showOnHomePage = showOnHomePage;
    }

    public Object getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(Object metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public Object getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(Object metaDescription) {
        this.metaDescription = metaDescription;
    }

    public Object getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(Object metaTitle) {
        this.metaTitle = metaTitle;
    }

    public Boolean getAllowCustomerReviews() {
        return allowCustomerReviews;
    }

    public void setAllowCustomerReviews(Boolean allowCustomerReviews) {
        this.allowCustomerReviews = allowCustomerReviews;
    }

    public Integer getApprovedRatingSum() {
        return approvedRatingSum;
    }

    public void setApprovedRatingSum(Integer approvedRatingSum) {
        this.approvedRatingSum = approvedRatingSum;
    }

    public Integer getNotApprovedRatingSum() {
        return notApprovedRatingSum;
    }

    public void setNotApprovedRatingSum(Integer notApprovedRatingSum) {
        this.notApprovedRatingSum = notApprovedRatingSum;
    }

    public Integer getApprovedTotalReviews() {
        return approvedTotalReviews;
    }

    public void setApprovedTotalReviews(Integer approvedTotalReviews) {
        this.approvedTotalReviews = approvedTotalReviews;
    }

    public Integer getNotApprovedTotalReviews() {
        return notApprovedTotalReviews;
    }

    public void setNotApprovedTotalReviews(Integer notApprovedTotalReviews) {
        this.notApprovedTotalReviews = notApprovedTotalReviews;
    }

    public Object getSku() {
        return sku;
    }

    public void setSku(Object sku) {
        this.sku = sku;
    }

    public Object getManufacturerPartNumber() {
        return manufacturerPartNumber;
    }

    public void setManufacturerPartNumber(Object manufacturerPartNumber) {
        this.manufacturerPartNumber = manufacturerPartNumber;
    }

    public Object getGtin() {
        return gtin;
    }

    public void setGtin(Object gtin) {
        this.gtin = gtin;
    }

    public Boolean getIsGiftCard() {
        return isGiftCard;
    }

    public void setIsGiftCard(Boolean isGiftCard) {
        this.isGiftCard = isGiftCard;
    }

    public Boolean getRequireOtherProducts() {
        return requireOtherProducts;
    }

    public void setRequireOtherProducts(Boolean requireOtherProducts) {
        this.requireOtherProducts = requireOtherProducts;
    }

    public Boolean getAutomaticallyAddRequiredProducts() {
        return automaticallyAddRequiredProducts;
    }

    public void setAutomaticallyAddRequiredProducts(Boolean automaticallyAddRequiredProducts) {
        this.automaticallyAddRequiredProducts = automaticallyAddRequiredProducts;
    }

    public Boolean getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(Boolean isDownload) {
        this.isDownload = isDownload;
    }

    public Boolean getUnlimitedDownloads() {
        return unlimitedDownloads;
    }

    public void setUnlimitedDownloads(Boolean unlimitedDownloads) {
        this.unlimitedDownloads = unlimitedDownloads;
    }

    public Integer getMaxNumberOfDownloads() {
        return maxNumberOfDownloads;
    }

    public void setMaxNumberOfDownloads(Integer maxNumberOfDownloads) {
        this.maxNumberOfDownloads = maxNumberOfDownloads;
    }

    public Object getDownloadExpirationDays() {
        return downloadExpirationDays;
    }

    public void setDownloadExpirationDays(Object downloadExpirationDays) {
        this.downloadExpirationDays = downloadExpirationDays;
    }

    public Boolean getHasSampleDownload() {
        return hasSampleDownload;
    }

    public void setHasSampleDownload(Boolean hasSampleDownload) {
        this.hasSampleDownload = hasSampleDownload;
    }

    public Boolean getHasUserAgreement() {
        return hasUserAgreement;
    }

    public void setHasUserAgreement(Boolean hasUserAgreement) {
        this.hasUserAgreement = hasUserAgreement;
    }

    public Boolean getIsRecurring() {
        return isRecurring;
    }

    public void setIsRecurring(Boolean isRecurring) {
        this.isRecurring = isRecurring;
    }

    public Integer getRecurringCycleLength() {
        return recurringCycleLength;
    }

    public void setRecurringCycleLength(Integer recurringCycleLength) {
        this.recurringCycleLength = recurringCycleLength;
    }

    public Integer getRecurringTotalCycles() {
        return recurringTotalCycles;
    }

    public void setRecurringTotalCycles(Integer recurringTotalCycles) {
        this.recurringTotalCycles = recurringTotalCycles;
    }

    public Boolean getIsRental() {
        return isRental;
    }

    public void setIsRental(Boolean isRental) {
        this.isRental = isRental;
    }

    public Integer getRentalPriceLength() {
        return rentalPriceLength;
    }

    public void setRentalPriceLength(Integer rentalPriceLength) {
        this.rentalPriceLength = rentalPriceLength;
    }

    public Boolean getIsShipEnabled() {
        return isShipEnabled;
    }

    public void setIsShipEnabled(Boolean isShipEnabled) {
        this.isShipEnabled = isShipEnabled;
    }

    public Boolean getIsFreeShipping() {
        return isFreeShipping;
    }

    public void setIsFreeShipping(Boolean isFreeShipping) {
        this.isFreeShipping = isFreeShipping;
    }

    public Boolean getShipSeparately() {
        return shipSeparately;
    }

    public void setShipSeparately(Boolean shipSeparately) {
        this.shipSeparately = shipSeparately;
    }

    public Integer getAdditionalShippingCharge() {
        return additionalShippingCharge;
    }

    public void setAdditionalShippingCharge(Integer additionalShippingCharge) {
        this.additionalShippingCharge = additionalShippingCharge;
    }

    public Boolean getIsTaxExempt() {
        return isTaxExempt;
    }

    public void setIsTaxExempt(Boolean isTaxExempt) {
        this.isTaxExempt = isTaxExempt;
    }

    public Boolean getIsTelecommunicationsOrBroadcastingOrElectronicServices() {
        return isTelecommunicationsOrBroadcastingOrElectronicServices;
    }

    public void setIsTelecommunicationsOrBroadcastingOrElectronicServices(Boolean isTelecommunicationsOrBroadcastingOrElectronicServices) {
        this.isTelecommunicationsOrBroadcastingOrElectronicServices = isTelecommunicationsOrBroadcastingOrElectronicServices;
    }

    public Boolean getUseMultipleWarehouses() {
        return useMultipleWarehouses;
    }

    public void setUseMultipleWarehouses(Boolean useMultipleWarehouses) {
        this.useMultipleWarehouses = useMultipleWarehouses;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Boolean getDisplayStockAvailability() {
        return displayStockAvailability;
    }

    public void setDisplayStockAvailability(Boolean displayStockAvailability) {
        this.displayStockAvailability = displayStockAvailability;
    }

    public Boolean getDisplayStockQuantity() {
        return displayStockQuantity;
    }

    public void setDisplayStockQuantity(Boolean displayStockQuantity) {
        this.displayStockQuantity = displayStockQuantity;
    }

    public Integer getMinStockQuantity() {
        return minStockQuantity;
    }

    public void setMinStockQuantity(Integer minStockQuantity) {
        this.minStockQuantity = minStockQuantity;
    }

    public Integer getNotifyAdminForQuantityBelow() {
        return notifyAdminForQuantityBelow;
    }

    public void setNotifyAdminForQuantityBelow(Integer notifyAdminForQuantityBelow) {
        this.notifyAdminForQuantityBelow = notifyAdminForQuantityBelow;
    }

    public Boolean getAllowBackInStockSubscriptions() {
        return allowBackInStockSubscriptions;
    }

    public void setAllowBackInStockSubscriptions(Boolean allowBackInStockSubscriptions) {
        this.allowBackInStockSubscriptions = allowBackInStockSubscriptions;
    }

    public Integer getOrderMinimumQuantity() {
        return orderMinimumQuantity;
    }

    public void setOrderMinimumQuantity(Integer orderMinimumQuantity) {
        this.orderMinimumQuantity = orderMinimumQuantity;
    }

    public Integer getOrderMaximumQuantity() {
        return orderMaximumQuantity;
    }

    public void setOrderMaximumQuantity(Integer orderMaximumQuantity) {
        this.orderMaximumQuantity = orderMaximumQuantity;
    }

    public Object getAllowedQuantities() {
        return allowedQuantities;
    }

    public void setAllowedQuantities(Object allowedQuantities) {
        this.allowedQuantities = allowedQuantities;
    }

    public Boolean getAllowAddingOnlyExistingAttributeCombinations() {
        return allowAddingOnlyExistingAttributeCombinations;
    }

    public void setAllowAddingOnlyExistingAttributeCombinations(Boolean allowAddingOnlyExistingAttributeCombinations) {
        this.allowAddingOnlyExistingAttributeCombinations = allowAddingOnlyExistingAttributeCombinations;
    }

    public Boolean getDisableBuyButton() {
        return disableBuyButton;
    }

    public void setDisableBuyButton(Boolean disableBuyButton) {
        this.disableBuyButton = disableBuyButton;
    }

    public Boolean getDisableWishlistButton() {
        return disableWishlistButton;
    }

    public void setDisableWishlistButton(Boolean disableWishlistButton) {
        this.disableWishlistButton = disableWishlistButton;
    }

    public Boolean getAvailableForPreOrder() {
        return availableForPreOrder;
    }

    public void setAvailableForPreOrder(Boolean availableForPreOrder) {
        this.availableForPreOrder = availableForPreOrder;
    }

    public Object getPreOrderAvailabilityStartDateTimeUtc() {
        return preOrderAvailabilityStartDateTimeUtc;
    }

    public void setPreOrderAvailabilityStartDateTimeUtc(Object preOrderAvailabilityStartDateTimeUtc) {
        this.preOrderAvailabilityStartDateTimeUtc = preOrderAvailabilityStartDateTimeUtc;
    }

    public Boolean getCallForPrice() {
        return callForPrice;
    }

    public void setCallForPrice(Boolean callForPrice) {
        this.callForPrice = callForPrice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Integer oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Integer getProductCost() {
        return productCost;
    }

    public void setProductCost(Integer productCost) {
        this.productCost = productCost;
    }

    public Object getSpecialPrice() {
        return specialPrice;
    }

    public void setSpecialPrice(Object specialPrice) {
        this.specialPrice = specialPrice;
    }

    public Object getSpecialPriceStartDateTimeUtc() {
        return specialPriceStartDateTimeUtc;
    }

    public void setSpecialPriceStartDateTimeUtc(Object specialPriceStartDateTimeUtc) {
        this.specialPriceStartDateTimeUtc = specialPriceStartDateTimeUtc;
    }

    public Object getSpecialPriceEndDateTimeUtc() {
        return specialPriceEndDateTimeUtc;
    }

    public void setSpecialPriceEndDateTimeUtc(Object specialPriceEndDateTimeUtc) {
        this.specialPriceEndDateTimeUtc = specialPriceEndDateTimeUtc;
    }

    public Boolean getCustomerEntersPrice() {
        return customerEntersPrice;
    }

    public void setCustomerEntersPrice(Boolean customerEntersPrice) {
        this.customerEntersPrice = customerEntersPrice;
    }

    public Integer getMinimumCustomerEnteredPrice() {
        return minimumCustomerEnteredPrice;
    }

    public void setMinimumCustomerEnteredPrice(Integer minimumCustomerEnteredPrice) {
        this.minimumCustomerEnteredPrice = minimumCustomerEnteredPrice;
    }

    public Integer getMaximumCustomerEnteredPrice() {
        return maximumCustomerEnteredPrice;
    }

    public void setMaximumCustomerEnteredPrice(Integer maximumCustomerEnteredPrice) {
        this.maximumCustomerEnteredPrice = maximumCustomerEnteredPrice;
    }

    public Boolean getBasepriceEnabled() {
        return basepriceEnabled;
    }

    public void setBasepriceEnabled(Boolean basepriceEnabled) {
        this.basepriceEnabled = basepriceEnabled;
    }

    public Integer getBasepriceAmount() {
        return basepriceAmount;
    }

    public void setBasepriceAmount(Integer basepriceAmount) {
        this.basepriceAmount = basepriceAmount;
    }

    public Integer getBasepriceBaseAmount() {
        return basepriceBaseAmount;
    }

    public void setBasepriceBaseAmount(Integer basepriceBaseAmount) {
        this.basepriceBaseAmount = basepriceBaseAmount;
    }

    public Boolean getHasTierPrices() {
        return hasTierPrices;
    }

    public void setHasTierPrices(Boolean hasTierPrices) {
        this.hasTierPrices = hasTierPrices;
    }

    public Boolean getHasDiscountsApplied() {
        return hasDiscountsApplied;
    }

    public void setHasDiscountsApplied(Boolean hasDiscountsApplied) {
        this.hasDiscountsApplied = hasDiscountsApplied;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Object getAvailableStartDateTimeUtc() {
        return availableStartDateTimeUtc;
    }

    public void setAvailableStartDateTimeUtc(Object availableStartDateTimeUtc) {
        this.availableStartDateTimeUtc = availableStartDateTimeUtc;
    }

    public Object getAvailableEndDateTimeUtc() {
        return availableEndDateTimeUtc;
    }

    public void setAvailableEndDateTimeUtc(Object availableEndDateTimeUtc) {
        this.availableEndDateTimeUtc = availableEndDateTimeUtc;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getCreatedOnUtc() {
        return createdOnUtc;
    }

    public void setCreatedOnUtc(String createdOnUtc) {
        this.createdOnUtc = createdOnUtc;
    }

    public String getUpdatedOnUtc() {
        return updatedOnUtc;
    }

    public void setUpdatedOnUtc(String updatedOnUtc) {
        this.updatedOnUtc = updatedOnUtc;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer getParentGroupedProductId() {
        return parentGroupedProductId;
    }

    public void setParentGroupedProductId(Integer parentGroupedProductId) {
        this.parentGroupedProductId = parentGroupedProductId;
    }

    public Object getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Object roleIds) {
        this.roleIds = roleIds;
    }

    public Object getDiscountIds() {
        return discountIds;
    }

    public void setDiscountIds(Object discountIds) {
        this.discountIds = discountIds;
    }

    public Object getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(Object storeIds) {
        this.storeIds = storeIds;
    }

    public Object getManufacturerIds() {
        return manufacturerIds;
    }

    public void setManufacturerIds(Object manufacturerIds) {
        this.manufacturerIds = manufacturerIds;
    }

    public Object getImages() {
        return images;
    }

    public void setImages(Object images) {
        this.images = images;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public Object getAssociatedProductIds() {
        return associatedProductIds;
    }

    public void setAssociatedProductIds(Object associatedProductIds) {
        this.associatedProductIds = associatedProductIds;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Object getSeName() {
        return seName;
    }

    public void setSeName(Object seName) {
        this.seName = seName;
    }
}
