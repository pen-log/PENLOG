import { useState } from 'react';
import styles from '../css/main.module.scss';
import data from '../dummy/data'
import { AiOutlineMore, AiOutlineRise } from "react-icons/ai";
import { BsClock } from "react-icons/bs";
import { useLocation, useNavigate } from "react-router-dom";

function Main() {
    const navigate = useNavigate()
    const location = useLocation()
    const [isOpen, setIsOpen] = useState(false);
    const toggleDropdown = () => {
        setIsOpen(!isOpen);
    }
    return (<>
        <div className={styles.category}>
            <div className={styles.category_content}>
                <li className={location.pathname === '/' ? styles.selected : ""} onClick={() => navigate('/')}><AiOutlineRise /><span>블로그</span></li>
                <li className={location.pathname === '/recent' ? styles.selected : ""} onClick={() => navigate('/recent')}><BsClock /><span>최신</span></li>
                <select>
                    <option value="apple">오늘</option>
                    <option value="banana">이번주</option>
                    <option value="orange">이번달</option>
                    <option value="grape">올해</option>
                </select>
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
        <div className={styles.main_container}>
            {data.map((a, i) => (
                <div className={styles.main_item} key={i}>
                    <div className={styles.main_img}>
                        <img src="velog.png" onClick={() => navigate('/detail/' + a.title)} />
                    </div>
                    <div className={styles.main_info}>
                        <div className={styles.main_title}>{a.title}</div>
                        <div className={styles.main_content}>{a.content}</div>
                        <div className={styles.main_period}>{a.period}</div>
                        <div className={styles.main_name}><span onClick={() => navigate('/user/' + a.name)}>{a.name}</span></div>
                    </div>
                </div>
            ))}
        </div>
    </>
    )
}

export default Main
