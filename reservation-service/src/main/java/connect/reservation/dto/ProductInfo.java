package connect.reservation.dto;

public class ProductInfo {
	private int productId;
	private int categoryId;
	private String productName;
	private String description;
	private String placeName;
	private int type;
	private String fileName;
	private String saveFileName;
	
	private String placeLot;
	private String placeStreet;
	private String tel;
	private String homepage;
	private String email;
	private String content;
	private String observationTime;
	private String displayStart;
	private String displayEnd;
	private String event;
	private String salesEnd;
	private int salesFlag;
	
	private int priceType;
	private int price;
	private double discountRate;
	private double discountPrice;
	private int minimumPrice;
	
	
	public double getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}
	public int getMinimumPrice() {
		return minimumPrice;
	}
	public void setMinimumPrice(int minimumPrice) {
		this.minimumPrice = minimumPrice;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSaveFileName() {
		return saveFileName;
	}
	public void setSaveFileName(String saveFileName) {
		this.saveFileName = saveFileName;
	}
	public String getPlaceLot() {
		return placeLot;
	}
	public void setPlaceLot(String placeLot) {
		this.placeLot = placeLot;
	}
	public String getPlaceStreet() {
		return placeStreet;
	}
	public void setPlaceStreet(String placeStreet) {
		this.placeStreet = placeStreet;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getObservationTime() {
		return observationTime;
	}
	public void setObservationTime(String observationTime) {
		this.observationTime = observationTime;
	}
	public String getDisplayStart() {
		return displayStart;
	}
	public void setDisplayStart(String displayStart) {
		this.displayStart = displayStart;
	}
	public String getDisplayEnd() {
		return displayEnd;
	}
	public void setDisplayEnd(String displayEnd) {
		this.displayEnd = displayEnd;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getSalesEnd() {
		return salesEnd;
	}
	public void setSalesEnd(String salesEnd) {
		this.salesEnd = salesEnd;
	}
	public int getSalesFlag() {
		return salesFlag;
	}
	public void setSalesFlag(int salesFlag) {
		this.salesFlag = salesFlag;
	}
	public int getPriceType() {
		return priceType;
	}
	public void setPriceType(int priceType) {
		this.priceType = priceType;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public double getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}
	
	
	@Override
	public String toString() {
		return "ProductInfo [productId=" + productId + ", categoryId=" + categoryId + ", productName=" + productName
				+ ", description=" + description + ", placeName=" + placeName + ", type=" + type + ", fileName="
				+ fileName + ", saveFileName=" + saveFileName + ", placeLot=" + placeLot + ", placeStreet="
				+ placeStreet + ", tel=" + tel + ", homepage=" + homepage + ", email=" + email + ", content=" + content
				+ ", observationTime=" + observationTime + ", displayStart=" + displayStart + ", displayEnd="
				+ displayEnd + ", event=" + event + ", salesEnd=" + salesEnd + ", salesFlag=" + salesFlag
				+ ", priceType=" + priceType + ", price=" + price + ", discountRate=" + discountRate + "]";
	}
	
}
