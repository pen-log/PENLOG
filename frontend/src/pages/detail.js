import { BsChatHeart } from "react-icons/bs";
import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import styles from '../css/detail.module.scss'
import axios from 'axios'
const Detail = () => {
    const navigate = useNavigate()
    const [result, setResult] = useState([])
    const { id } = useParams();
    const item = result.find(item => item.title === id);
    useEffect(() => {
        axios.get('http://localhost:3000/data').then((res) => {
            setResult(res.data)
        }).catch((err) => {
            console.log('에러임', err)
        })
    }, [])
    if (!item) {
        return (
            <div className={styles.container}>
                <p>해당 아이템을 찾을 수 없습니다.</p>
            </div>
        );
    }
    return (
        <div className={styles.container}>
            <div className={styles.title}>
                <h1>{item.title}</h1>
            </div>
            <div className={styles.content}>
                <h3>{item.content}</h3>
            </div>
            <div className={styles.info}>
                <div className={styles.info1}>
                    <p>작성자 : <span onClick={() => {
                        navigate('/user/' + item.name)
                    }}>{item.name}</span></p></div>
                <div className={styles.info1}><p>{item.period}</p></div>
                <div className={styles.info1}><p>좋아요 <BsChatHeart /></p></div>
            </div>
            <div className={styles.content}>
                <p>{item.info}</p>
            </div>

            <div className={styles.comment_container}>
                <div className={styles.comment}>
                    <h4>댓글</h4>
                    <textarea placeholder="댓글 적어주세요" />
                </div>
                <div className={styles.comment_button}>
                    <button>댓글 작성</button>
                </div>
                <div className={styles.visitor}>
                    <div className={styles.visitor_profile}>
                        <div className={styles.visitor_img}>
                            <img src="me.jpg" />1님의 아이디 ({item.period})
                        </div>
                        <div className={styles.visitor_content}>와 멋있어요!!</div>
                    </div>
                </div>

            </div>
        </div>
    )
};

export default Detail;
