import React, { useEffect, useState } from "react";
import axios from "axios";
import { ProductsContainer } from "../Product/ProductsContainer";
import { ProductMenu } from "../Product/ProductMenu";
import { getProducts } from "../../api/productsAPI";
import { ProductInterface } from "../../interfaces/ProductInterface";
import { CategoryInterface } from "../../interfaces/CategoryInterface"
import { Page } from "./Page";
import { Navbar, defaultLinks } from "../Reusable/Navbar";
import { backend } from "../../App";

export const ProductsPage: React.FC = () => {
	const [products, setProducts] = useState([] as ProductInterface[]);
	const [searchQuery, setSearchQuery] = useState('');
	const [priceRange, setPriceRange] = useState({ min: 0, max: 250 });
	const [filterBySearch, setFilterBySearch] = useState(true);
	const [filterByPrice, setFilterByPrice] = useState(true);

	const [categories, setCategories] = useState([] as CategoryInterface[]);
	const [selectedCategories, setSelectedCategories] = useState<number[]>([]);
	const [filterByCategory, setFilterByCategory] = useState(true);

	useEffect(() => {
		getAllProducts();
		getAllCategories();
	}, []);

	async function getAllCategories() {
		try {
			const response = await axios.get(backend("/categories"));
			setCategories(response.data);
		} catch (e: any) {
			console.log(e);
		}
	}

	async function getAllProducts() {
		try {
			const response = await getProducts();
			setProducts(response.data);
		} catch (e: any) {
			console.log(e);
		}
	}

	const filterProducts = (product: ProductInterface) => {
		// Filter by search query
		const matchesSearch = !filterBySearch || product.name.toLowerCase().includes(searchQuery.toLowerCase());

		// Filter by selected categories
		const matchesCategories = !filterByCategory || selectedCategories.length === 0 || selectedCategories.includes(product.category.categoryId);

		// Filter by price range
		const matchesPriceRange = !filterByPrice || (product.cost >= priceRange.min && product.cost <= priceRange.max);

		return matchesSearch && matchesCategories && matchesPriceRange;
		//return matchesSearch && matchesPriceRange;

	};

	const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
		setSearchQuery(event.target.value);
	};

	const handleCategoryChange = (categoryId: number) => {
		setSelectedCategories(prev =>
			prev.includes(categoryId)
				? prev.filter(catId => catId !== categoryId)
				: [...prev, categoryId]
		);
	};

	const handlePriceChange = (event: React.ChangeEvent<HTMLInputElement>) => {
		const newValue = Number(event.target.value);
		setPriceRange({ ...priceRange, max: newValue });
	};

	const toggleFilterBySearch = () => {
		setFilterBySearch(prevState => !prevState);
	};

	const toggleFilterByCategory = () => {
		setFilterByCategory(prevState => !prevState);
	};

	const toggleFilterByPrice = () => {
		setFilterByPrice(prevState => !prevState);
	};

	const renderProductList = () => {
		const filteredProducts = products.filter(filterProducts);
		if (filteredProducts.length === 0) {
			return <p>No Products Found</p>;
		} else {
			return <ProductsContainer products={filteredProducts} />;
		}
	};

	return (
		<div>
			<div>
				<Navbar links={defaultLinks} />
			</div>
			<Page>
				<h2>SmileMart</h2>
				<ProductMenu />
				{renderProductList()}
				<div>
					<div>
						<h2>Search</h2>
						<input type="text" value={searchQuery} onChange={handleSearchChange} />
						<button onClick={toggleFilterBySearch}>{filterBySearch ? 'Disable' : 'Enable'} Search Filter</button>
					</div>
					<div>
						<h2>Categories</h2>
						{categories.map(category => (
							<li key={category.categoryId}>
								<label>
									<input
										type="checkbox"
										checked={selectedCategories.includes(category.categoryId)}
										onChange={() => handleCategoryChange(category.categoryId)}
									/>
									{category.description}
								</label>
							</li>
						))}
						<button onClick={toggleFilterByCategory}>{filterByCategory ? 'Disable' : 'Enable'} Category Filter</button>
					</div>
					<div>
						<h2>Price</h2>
						<input
							type="range"
							min={0}
							max={250}
							value={priceRange.max}
							onChange={handlePriceChange}
						/>
						<p>Price: ${priceRange.max}.00</p>
						<button onClick={toggleFilterByPrice}>{filterByPrice ? 'Disable' : 'Enable'} Price Filter</button>
					</div>
				</div>
			</Page>
		</div>
	);
};

export default ProductsPage;
