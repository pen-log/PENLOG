import { BsSearch } from "react-icons/bs";
import styles from '../css/search.module.scss'

function Search() {
    return (
        <div className={styles.search_container}>
            <form className={styles.search_bar}>
                <BsSearch className={styles.search_icon} />
                <input type="search" placeholder="검색어를 입력하세요" />
            </form>
        </div>
    )
}

export default Search
