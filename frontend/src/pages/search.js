import { BsSearch } from "react-icons/bs";
import styles from '../css/search.module.scss';
import { useState } from "react";
import data from "../dummy/data";
import { useNavigate } from 'react-router-dom';

function Search() {
    const navigate = useNavigate()
    const [userInput, setUserInput] = useState('');
    const [searched, setSearched] = useState([]);

    const handleInputChange = (e) => {
        const target = e.target.value;
        setUserInput(target);
        {
            target.trim() === '' ||
                setSearched(data.filter((a) => a.content.includes(target) || a.title.includes(target))
                    .map((a, i) =>
                        <div className={styles.board} key={i} onClick={() => navigate('/detail/' + a.title)}>
                            <h1>{a.title}</h1>
                            <h3>{a.content}</h3>
                        </div>
                    )
                )
        }
    };

    return (
        <div className={styles.container}>
            <form className={styles.search_bar}>
                <BsSearch className={styles.icon} />
                <input
                    type="search"
                    value={userInput}
                    onChange={handleInputChange}
                    placeholder="검색어를 입력하세요"
                />
            </form>
            {searched.length > 0 && (
                <div className={styles.card_container}>
                    {searched.map((a, i) => (
                        <div className={styles.card} key={i}>
                            <p>{a}</p>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}

export default Search;
