import { BsXLg, BsGithub, BsGoogle, BsFacebook } from "react-icons/bs";
import { useNavigate } from "react-router-dom";
import styles from '../css/signup.module.scss';
import { useState } from "react";

export default function Signup() {
    const navigate = useNavigate()
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log('Email:', email);
        console.log('Password:', password);
    };
    return (

        <div className={styles.login_container}>
            <div className={styles.login_exit}>
                < BsXLg onClick={() => { navigate('/') }} />
            </div>
            <div className={styles.login_box}>
                <div className={styles.login_left}>
                    <div className={styles.login_img}>
                        <img src={"https://static.velog.io/static/media/undraw_joyride_hnno.fae6b95e.svg"} />
                    </div>
                </div>
                <div className={styles.login_right}>
                    <div className={styles.login_content}>
                        <h2>회원가입</h2 >
                        <h4> 이메일로 회원가입</h4 >
                        <div className={styles.login_content_email}>
                            <form className={styles.login_form} >
                                <input type='email' placeholder='이메일을 입력하세요' />
                                <button type="submit">로그인</button>
                            </form >
                        </div >
                        <div className={styles.login_content_social} >
                            < h4 > 소셜 계정으로 회원가입</h4 >
                            <div className={styles.login_content_icon}>
                                < div > <BsGithub /></div >
                                <div><BsGoogle /></div>
                                <div><BsFacebook /></div>
                            </div >
                        </div >
                        <div className={styles.login_content_footer}>
                            <h4>아직 회원이 아니신가요?</h4>
                            <h4><span onClick={() => navigate('/login')}>회원가입</span></h4>
                        </div >
                    </div >
                </div >
            </div >
        </div>
    )
}
