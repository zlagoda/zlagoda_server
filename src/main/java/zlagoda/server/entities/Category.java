package zlagoda.server.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table("Category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
   @Id
   @Column("category_number")
   private long id;

   @Column("category_name")
   private String name;
}
