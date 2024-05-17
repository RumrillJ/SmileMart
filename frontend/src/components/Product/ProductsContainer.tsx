import { ProductInterface } from "../../interfaces/ProductInterface"
import { ProductItem } from "./ProductItem"

interface Props {
	products: ProductInterface[]
	onChange?: () => void
}

export const ProductsContainer: React.FC<Props> = ({ products, onChange }) => {
	return (
		<div>
			{products.map((p, index) => {
				return <ProductItem product={p} key={"prd" + index} onDelete={onChange} />
			})}
		</div>
	)
}