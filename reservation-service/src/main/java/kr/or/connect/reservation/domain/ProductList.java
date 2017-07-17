package kr.or.connect.reservation.domain;

public class ProductList {
	int id;
	String name;
	String placeName;
	String content;
	String fileName;
	String saveFileName;
	
	public ProductList(){
		
	}
	public ProductList(int id){
		this.id = id;
	}
	
	public ProductList(String name){
		this.name = name;
	}
	public ProductList(int id, String name){
		this.id = id;
		this.name = name;
	}
	public ProductList(int id, String name, String placeName){
		this.id = id;
		this.name = name;
		this.placeName = placeName;
	}
	public ProductList(int id, String name, String placeName, String content){
		this.id = id;
		this.name = name;
		this.placeName = placeName;
		this.content = content;
	}
	public ProductList(int id, String name, String placeName, String content, String fileName){
		this.id = id;
		this.name = name;
		this.placeName = placeName;
		this.content = content;
		this.fileName = fileName;
	}
	public ProductList(int id, String name, String placeName, String content, String fileName, String saveFileName){
		this.id = id;
		this.name = name;
		this.placeName = placeName;
		this.content = content;
		this.fileName = fileName;
		this.saveFileName = saveFileName;
	}
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	
	
}
