import { useState } from "react"
import { ProductInterface } from "../../interfaces/ProductInterface"

interface Props {
	onSubmit?: (product: ProductInterface) => void
}

export const AddProduct: React.FC<Props> = ({ onSubmit }) => {

	const [product, setProduct] = useState({} as ProductInterface)

	const handleSubmit = () => {
		// send post request here or in parent?
		if (onSubmit) {
			onSubmit({...product})
		}
		setProduct({} as ProductInterface)
	}

	return (
		<div className="add-product">
			<label className="add-product-name">
				Name:
				<input
					type="text"
					value={product.name ?? ""}
					onChange={(input) =>
						setProduct({ ...product, name: input.target.value })
					}
				/>
			</label>
			<label className="add-product-description">
				Description:
				<input
					type="text"
					value={product.description ?? ""}
					onChange={(input) =>
						setProduct({
							...product,
							description: input.target.value,
						})
					}
				/>
			</label>
			<label className="add-product-category">
				Category:
				<input
					type="text"
					value={product.category ?? ""}
					onChange={(input) =>
						setProduct({ ...product, category: input.target.value })
					}
				/>
			</label>
			<label className="add-product-price">
				Price:
				<input
					type="number"
					value={product.price ?? ""}
					onChange={(input) =>
						setProduct({
							...product,
							price: input.target.valueAsNumber,
						})
					}
				/>
			</label>

			<button onClick={handleSubmit}>Submit</button>

			{/* For testing.. remove later */}
			<h3>{JSON.stringify(product)}</h3>
		</div>
	)
}