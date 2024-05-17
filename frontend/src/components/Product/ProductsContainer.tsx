import { ProductInterface } from "../../interfaces/ProductInterface"
import { ProductItem } from "./ProductItem"

interface Props {
	products: ProductInterface[]
}

export const ProductsContainer: React.FC<Props> = ({ products }) => {
	return (
		<div>
			{products.map((p, index) => {
				return <ProductItem product={p} key={"prd" + index} />
			})}
		</div>
	)
}