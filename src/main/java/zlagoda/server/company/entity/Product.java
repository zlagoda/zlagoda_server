package zlagoda.server.company.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {
   private long id; 
   private Category category;
   private String name;
   private String characteristics;
}
