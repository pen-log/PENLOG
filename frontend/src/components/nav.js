import { BsFillSunFill, BsFillMoonFill, BsSearch } from "react-icons/bs";
import { useNavigate, useParams } from "react-router-dom";
import styles from '../css/nav.module.scss';
import { useState } from "react";
import data from "../dummy/data";
export default function Nav({ darkMode, ismode }) {
    const navigate = useNavigate()
    const [logo, setLogo] = useState('PenLog');
    return (<>
        <div className={styles.navbar}>
            <div className={styles.nav_logo} onClick={() => navigate('/')}>
                {logo}
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
    </>
    )
}
