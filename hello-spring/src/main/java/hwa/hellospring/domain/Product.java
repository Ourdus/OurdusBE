package hwa.hellospring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {

   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id private int product_id;
   private String author_id;
   private int category_id;
   private String product_name ;
   private int product_price;
   private int product_rate;
   private int product_review;
   private int product_hit;
   private int product_purchase;
   private int product_option;

   //getter and setter

   public int getProduct_id() {
      return product_id;
   }

   public void setProduct_id(int product_id) {
      this.product_id = product_id;
   }

   public String getAuthor_id() {
      return author_id;
   }

   public void setAuthor_id(String author_id) {
      this.author_id = author_id;
   }

   public int getCategory_id() {
      return category_id;
   }

   public void setCategory_id(int category_id) {
      this.category_id = category_id;
   }

   public String getProduct_name() {
      return product_name;
   }

   public void setProduct_name(String product_name) {
      this.product_name = product_name;
   }

   public int getProduct_price() {
      return product_price;
   }

   public void setProduct_price(int product_price) {
      this.product_price = product_price;
   }

   public int getProduct_rate() {
      return product_rate;
   }

   public void setProduct_rate(int product_rate) {
      this.product_rate = product_rate;
   }

   public int getProduct_review() {
      return product_review;
   }

   public void setProduct_review(int product_review) {
      this.product_review = product_review;
   }

   public int getProduct_hit() {
      return product_hit;
   }

   public void setProduct_hit(int product_hit) {
      this.product_hit = product_hit;
   }

   public int getProduct_purchase() {
      return product_purchase;
   }

   public void setProduct_purchase(int product_purchase) {
      this.product_purchase = product_purchase;
   }

   public int getProduct_option() {
      return product_option;
   }

   public void setProduct_option(int product_option) {
      this.product_option = product_option;
   }



}
