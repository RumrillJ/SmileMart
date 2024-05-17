import { useState } from "react"
import { ProductInterface } from "../../interfaces/ProductInterface"
import { insertProduct } from "../../api/productsAPI"

interface Props {
	onSubmit?: () => void
}

export const AddProduct: React.FC<Props> = ({ onSubmit }) => {

	const [product, setProduct] = useState({} as ProductInterface)

	async function handleSubmit() {
		try {
			const response = await insertProduct(product)
			setProduct({} as ProductInterface)
		} catch (e: any) {
			console.log(e)
		}
		if (onSubmit) {
			onSubmit()
		}
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
					value={product.cost ?? ""}
					onChange={(input) =>
						setProduct({
							...product,
							cost: input.target.valueAsNumber,
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