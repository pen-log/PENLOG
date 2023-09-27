// Nav.js
import { BsFillSunFill, BsFillMoonFill, BsSearch } from "react-icons/bs";
import { useNavigate, useLocation } from "react-router-dom";
import styles from '../css/nav.module.scss';
import { useState, useEffect } from "react";

export default function Nav({ darkMode, ismode }) {
    const navigate = useNavigate();
    const location = useLocation();
    const [logo, setLogo] = useState('PenLog');

    useEffect(() => {
        if (location.pathname.includes("/user/")) {
            const userId = location.pathname.split("/user/")[1];
            setLogo(userId);
        } else {
            setLogo('Penlog');
        }
    }, [location.pathname]);

    return (
        <>
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
    );
}
