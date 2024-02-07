package onlinefood;

public class FoodItems {
  private int foodId;
  private String itemName;
  private String category;
  private int price;
  private int quantity;

  
  public FoodItems()
  {
	  
  }

public int getFoodId() {
	return foodId;
}

public void setFoodId(int foodId) {
	this.foodId = foodId;
}

public String getItemName() {
	return itemName;
}

public void setItemName(String itemName) {
	this.itemName = itemName;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}

public int getPrice() {
	return price;
}

public void setPrice(int price) {
	this.price = price;
}


public int getQuantity() {
	return quantity;
}

public void setQuantity(int quantity) {
	this.quantity = quantity;
}

@Override
public String toString() {
	return "FoodItems [foodId=" + foodId + ", itemName=" + itemName + ", category=" + category + ", price=" + price
			+ ", quantity=" + quantity + "]";
}
  
}
