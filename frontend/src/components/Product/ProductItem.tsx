import { ProductInterface } from "../../interfaces/ProductInterface"

interface Props {
	product: ProductInterface
}

export const ProductItem: React.FC<Props> = ({product}) => {

	return (
		<div>
			{JSON.stringify(product)}
		</div>
	)
}