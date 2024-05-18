import React, { useState } from "react";
import { ProductInterface } from "../../interfaces/ProductInterface";
import { CategoryInterface } from "../../interfaces/CategoryInterface";

interface Props {
  onSubmit?: (product: ProductInterface) => void;
}

export const AddProduct: React.FC<Props> = ({ onSubmit }) => {
  const initialProductState: ProductInterface = {
    productId: 0,
    name: "",
    description: "",
    category: { categoryId: 0, description: "" },
    cost: 0,
  };

  const [product, setProduct] = useState(initialProductState);

  const handleSubmit = () => {
    if (onSubmit) {
      onSubmit({ ...product });
    }
    setProduct(initialProductState);
  };

  const handleInputChange = (
    event: React.ChangeEvent<HTMLInputElement>,
    propertyName: string
  ) => {
    const value =
      propertyName === "cost"
        ? Number(event.target.value)
        : event.target.value;
    setProduct({ ...product, [propertyName]: value });
  };

  return (
    <div className="add-product">
      <label className="add-product-name">
        Name:
        <input
          type="text"
          value={product.name}
          onChange={(e) => handleInputChange(e, "name")}
        />
      </label>
      <label className="add-product-description">
        Description:
        <input
          type="text"
          value={product.description}
          onChange={(e) => handleInputChange(e, "description")}
        />
      </label>
      <label className="add-product-category">
        Category:
        <input
          type="text"
          value={product.category.description}
          onChange={(e) => handleInputChange(e, "category")}
        />
      </label>
      <label className="add-product-cost">
        Price:
        <input
          type="number"
          value={product.cost}
          onChange={(e) => handleInputChange(e, "cost")}
        />
      </label>
      <button onClick={handleSubmit}>Submit</button>
    </div>
  );
};
