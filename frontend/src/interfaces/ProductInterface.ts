import { CategoryInterface } from "./CategoryInterface"

export interface ProductInterface {
	productId: number
	name: string
	description: string
	category: CategoryInterface
	cost: number
	quantity?: number
}