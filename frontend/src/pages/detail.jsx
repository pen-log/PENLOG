import { BsChatHeart } from "react-icons/bs";

import React from 'react';
import { useParams } from 'react-router-dom';
import styles from '../css/detail.module.scss'
import data from '../dummy/data';

const Detail = () => {
    const { title } = useParams();
    const item = data.find(item => item.title === title);
    return (
        <div className={styles.container}>

            <div className={styles.title}>
                <h1>@{item.title}</h1>
            </div>
            <div className={styles.content}>
                <h1>{item.content}</h1>
            </div>
            <div className={styles.info}>
                <div className={styles.info1}><p>작성자 : {item.name}  </p></div>
                <div className={styles.info1}><p>{item.period}</p></div>
                <div className={styles.info1}><p>좋아요 <BsChatHeart /></p></div>
            </div>
            <div className={styles.content}>
                <p>{item.info}</p>
            </div>
            <div className={styles.댓글}>
                <h4>댓글</h4>
                <textarea placeholder="댓글 적어주세요" />
                <button>댓글 작성</button>
            </div>
            <div className={styles.댓글러들}>
                <div className={styles.댓글단사람프로필}>
                    <p> <img alt='프로필이미지' />  1님의 아이디 ({item.period})</p>
                </div>
                <div className={styles.댓글단사람의댓글}>와 멋있어요!!</div>
            </div>
        </div>
    );
};

export default Detail;
