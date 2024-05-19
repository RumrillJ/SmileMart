import { ProductInterface } from "../../interfaces/ProductInterface"
import { ProductItem } from "./ProductItem"
import "../../styles/productscontainer.css"

interface Props {
	products: ProductInterface[]
	onChange?: () => void
}

export const ProductsContainer: React.FC<Props> = ({ products, onChange }) => {
	return (
		<div className="products-container">
			{products.map((p, index) => {
				return <ProductItem product={p} key={"prd" + index} onDelete={onChange} />
			})}
		</div>
	)
}