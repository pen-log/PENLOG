import { useEffect, useState } from 'react';
import styles from '../css/main.module.scss';
import { AiOutlineMore, AiOutlineRise, AiOutlineSortAscending } from "react-icons/ai";
import { useNavigate } from "react-router-dom";
import axios from 'axios'

function Main() {
    const navigate = useNavigate()
    const [result, setResult] = useState([])
    const [isOpen, setIsOpen] = useState(false);
    const toggleDropdown = () => {
        setIsOpen(!isOpen);
    }
    const [selectedCategory, setSelectedCategory] = useState('블로그');
    const handleSelect = (a) => {
        setSelectedCategory(a)
    }
    const handleDetail = (a) => {
        navigate('/detail/' + a)
    }
    useEffect(() => {
        axios.get('http://localhost:3000/data')
            .then((res) => {
                if (selectedCategory === '최신') {
                    res.data.sort((a, b) => {
                        return new Date(b.period) - new Date(a.period);
                    });
                }
                setResult(res.data);
            }).catch((err) => {
                console.log('에러임', err)
            })
    }, [selectedCategory])

    return (<>
        <div className={styles.category}>
            <div className={styles.category_content}>
                <li className={selectedCategory === '블로그' ? styles.selected : ""}
                    onClick={() => {
                        handleSelect('블로그');
                    }}>
                    <AiOutlineRise /><span>블로그</span></li>
                <li className={selectedCategory === '최신' ? styles.selected : ""}
                    onClick={() => {
                        handleSelect('최신');
                    }}><AiOutlineSortAscending /><span>최신</span></li>
            </div>
            <div className={styles.category_more} >
                <AiOutlineMore className={styles.dropdown_icon} onClick={toggleDropdown} />
                <div className={` ${styles.dropdown_list} ${isOpen ? styles.active : ''}  `}>
                    <li>공지사항</li>
                    <li>태그 목록</li>
                    <li>서비스 정책</li>
                    <li>Slack</li>
                    <li>문의<br />abcd@naver.com</li>
                </div>
            </div>
        </div >
        <div className={styles.container}>
            {result.map((a, i) => (
                <div className={styles.post} key={i}>
                    <div className={styles.img}>
                        <img src="me2.jpg" onClick={() => handleDetail(a.title)} />
                    </div>
                    <div className={styles.info}>
                        <div className={styles.title} onClick={() => handleDetail(a.title)}>{a.title}</div>
                        <div className={styles.content} onClick={() => handleDetail(a.title)}>{a.content}</div>
                        <div className={styles.period}>{a.period}</div>
                        <div className={styles.profile}>
                            <div className={styles.profile_img}>
                                <img src='me.jpg' onClick={() => navigate('/user/' + a.name)} />
                            </div>
                            <div className={styles.profile_id} onClick={() => navigate('/user/' + a.name)}>{a.name}</div>
                        </div>
                    </div>
                </div>
            ))}
        </div>
    </>
    )
}

export default Main
