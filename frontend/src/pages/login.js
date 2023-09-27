import { BsXLg, BsGithub, BsGoogle, BsFacebook } from "react-icons/bs";
import { useNavigate } from "react-router-dom";
import styles from '../css/login.module.scss';
import { useState } from "react";
import axios from "axios";

export default function Login() {
    const navigate = useNavigate()
    const [inputs, setInputs] = useState({
        email: '',
        password: ''
    });

    const { email, password } = inputs;

    const onChange = (e) => {
        const { value, name } = e.target;
        setInputs({
            ...inputs,
            [name]: value
        });
    };
    const handleSubmit = (e) => {
        e.preventDefault()
        axios.post('http://localhost:3000/login', {
            email: email,
            password: password,
        })
            .then((res) => {
                if (res.data.token) {
                    const token = res.data.token;
                    console.log('로그인 성공', res);
                    localStorage.setItem('token', token);
                    navigate('/');
                } else {
                    console.error('로그인 실패: 토큰이 없음');
                }
            }).catch((error) => {
                console.error('로그인 실패', error);
            });
    }

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
                        <h2>로그인</h2 >
                        <h4> 이메일로 로그인</h4 >
                        <div className={styles.login_content_email}>
                            <form className={styles.login_form} onSubmit={handleSubmit}>
                                <input name="email" placeholder="이메일" onChange={onChange} value={email} />
                                <input name="password" placeholder="비밀번호" onChange={onChange} value={password} />
                                <button type="submit">로그인</button>
                            </form >
                        </div >
                        <div className={styles.login_content_social} >
                            < h4 > 소셜 계정으로 로그인</h4 >
                            <div className={styles.login_content_icon}>
                                < div > <BsGithub /></div >
                                <div><BsGoogle /></div>
                                <div><BsFacebook /></div>
                            </div >
                        </div >
                        <div className={styles.login_content_footer}>
                            <h4>아직 회원이 아니신가요?</h4>
                            <h4><span onClick={() => navigate('/signup')}>회원가입</span></h4>
                        </div >
                    </div >
                </div >
            </div >
        </div >
    )
}
