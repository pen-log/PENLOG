import { BsSearch } from "react-icons/bs";

function Search() {
    return (
        <div className="search-container">
            <form className="search-bar">
                <BsSearch className="search-icon" />
                <input type="search" placeholder="검색어를 입력하세요" />
            </form>
        </div>
    )
}

export default Search
