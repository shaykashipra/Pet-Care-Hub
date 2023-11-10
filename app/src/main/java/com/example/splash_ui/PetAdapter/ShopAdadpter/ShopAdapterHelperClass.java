
package com.example.splash_ui.PetAdapter.ShopAdadpter;

public class ShopAdapterHelperClass {
  int image;
  String title,description,discount,price;

  public ShopAdapterHelperClass(int image, String title, String description,String price, String discount) {
    this.image = image;
    this.title = title;
    this.description = description;
    this.discount = discount;
    this.price=price;
  }


  public int getImage() {
    return image;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public String getDiscount() {
    return discount;
  }

  public String getPrice() {
    return price;
  }

}

