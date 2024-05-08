import { useParams } from "react-router-dom"

export const ProductDetail: React.FC = () => {

	const {productId} = useParams()

	return (
		<div>
			<h3>Product #{productId}</h3>
		</div>
	)
}