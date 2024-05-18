
import React, { useEffect, useState } from 'react';
import { ProductInterface } from '../../interfaces/ProductInterface';
import axios from "axios";
import { backend } from "../../App";

interface CategoryInterface {
	id: number;
	description: string;
  }
  


export const Sidebar: React.FC = () => {

	// Search filter
	const [searchQuery, setSearchQuery] = useState('');
	// Price range filter
	const [priceRange, setPriceRange] = useState({ min: 0, max: 1000 });
	// Category filter
	const [categories, setCategories] = useState([] as CategoryInterface[])
	const [selectedCategories, setSelectedCategories] = useState<number[]>([]);

	const [hideProducts, setHideProducts] = useState<boolean>(false);

	async function getAllCategories() {
		try
		{
		const response = await axios.get(backend("/categories"))
		setCategories(() => response.data)
	}
		catch (e: any) 
		{
			console.log(e)
		}
	}

	useEffect(() => {
		getAllCategories()
	}, [])

	const filterProductsByCategory = (id: number) => {
		setSelectedCategories(prev =>
		  prev.includes(id) ? prev.filter(categoryId => categoryId !== id) : [...prev, id]
		);
		setHideProducts(!hideProducts);
	  };

	return (
		<div>
			<div/* class="searchbox"*/>
			<h2>Search</h2>
			<input type="text"/>
		</div>

			<div/* class="categorybox"*/>
			<h2>Categories</h2>
			{categories.map(category => (
			<li key={category.id}>
				<label>
				<input
					type="checkbox"
					//checked={selectedCategories.includes(category.id)}
					onChange={() => filterProductsByCategory(category.id)}
				/>
				{category.description}
				</label>
			</li>
			))}
		</div>
			<div/* class="pricebox"*/>
			<h2>Price</h2>
				<input type="range"/>
		</div>

	  </div>
	)
}

export default Sidebar;

import React, { useState } from 'react';

interface Category {
  id: number;
  name: string;
}

interface SidebarProps {
  categories: Category[];
  categoryName: string;
  minPrice: number;
  maxPrice: number;
  onCategoryChange: (selected: Category[]) => void;
}

const Sidebar: React.FC<SidebarProps> = ({
  categories,
  categoryName,
  minPrice,
  maxPrice,
  onCategoryChange,
}) => {
  const [searchTerm, setSearchTerm] = useState('');
  const [filteredCategories, setFilteredCategories] = useState<Category[]>(categories);
  const [collapsed, setCollapsed] = useState(false);

  const handleSearch = (event: React.ChangeEvent<HTMLInputElement>) => {
    const term = event.target.value;
    setSearchTerm(term);
    const filtered = categories.filter((category) =>
      category.name.toLowerCase().includes(term.toLowerCase())
    );
    setFilteredCategories(filtered);
  };

  const toggleCollapse = () => {
    setCollapsed(!collapsed);
  };

  const handleCheckboxChange = (categoryId: number) => {
    // Handle checkbox change logic
  };

  return (
    <div className="sidebar">
      <div className="search-group">
        <button onClick={toggleCollapse}>
          {collapsed ? `Expand ${categoryName}s` : `Collapse ${categoryName}s`}
        </button>
        <input
          type="text"
          placeholder={`Search ${categoryName}s`}
          value={searchTerm}
          onChange={handleSearch}
        />
        {!collapsed && (
          <ul>
            {filteredCategories.map((category) => (
              <li key={category.id}>
                <input
                  type="checkbox"
                  id={String(category.id)}
                  onChange={() => handleCheckboxChange(category.id)}
                />
                <label htmlFor={String(category.id)}>{category.name}</label>
              </li>
            ))}
          </ul>
        )}
      </div>
      <div className="price-range-slider">
        <h3>Price Range</h3>
        {minPrice !== undefined && maxPrice !== undefined && (
          <>
            <label htmlFor="minPrice">Min Price: ${minPrice}</label>
            <input type="range" id="minPrice" name="min" min={minPrice} max={maxPrice} value={minPrice} />
            <label htmlFor="maxPrice">Max Price: ${maxPrice}</label>
            <input type="range" id="maxPrice" name="max" min={minPrice} max={maxPrice} value={maxPrice} />
          </>
        )}
      </div>
    </div>
  );
};

export default Sidebar;
