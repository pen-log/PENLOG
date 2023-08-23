import { useState } from "react";
import { AiOutlineMore, AiOutlineRise } from "react-icons/ai";
import { BsClock, BsFillSunFill, BsFillMoonFill, BsSearch } from "react-icons/bs";
import { useLocation, useNavigate } from "react-router-dom";
import styles from '../css/nav.module.scss';


export default function Nav({ darkMode, ismode }) {
    const navigate = useNavigate()
    const location = useLocation()
    const [isOpen, setIsOpen] = useState(false);
    const toggleDropdown = () => {
        setIsOpen(!isOpen);
    }

    return (<>
        <div className={styles.navbar}>
            <div className={styles.nav_logo} onClick={() => navigate('/')}>
                Penlog
            </div>
            <div className={styles.nav_content}>
                <li onClick={darkMode}>
                    {ismode ? <BsFillMoonFill /> : <BsFillSunFill />}
                </li>
                <li onClick={() => navigate('/search')}>
                    <BsSearch />
                </li>
                <button className={styles.login_btn} onClick={() => navigate('/login')}>로그인</button>
            </div>
        </div>

        <div className={styles.category}>
            <div className={styles.category_content}>
                <li className={location.pathname === '/' ? styles.selected : ""} onClick={() => navigate('/')}><AiOutlineRise /><span>트렌딩</span></li>
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
    </>
    )
}
