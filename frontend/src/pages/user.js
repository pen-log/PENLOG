import React, { useState } from 'react';
import { useParams, Link, useNavigate } from 'react-router-dom';
import data from '../dummy/data';
import { BsGithub, BsFillTelephoneFill, BsFillHouseFill } from 'react-icons/bs';
import styles from '../css/user.module.scss';

const About = () => {
    const { id } = useParams();
    const user = data.filter(item => item.name === id);
    const navigate = useNavigate();
    const [isAboutVisible, setIsAboutVisible] = useState(false);

    const toggleContent = () => {
        setIsAboutVisible(!isAboutVisible);
    };

    return (
        <div className={styles.container}>
            <div className={styles.profile}>
                <div className={styles.profile_img}>
                    <img src="/me3.jpg" alt="Profile" />
                </div>
                <div className={styles.profile_id}>{id}</div>
            </div>

            <div className={styles.list}>
                <li className={!isAboutVisible ? styles.selected : ''} onClick={() => toggleContent()}>
                    글목록
                </li>
                <li className={isAboutVisible ? styles.selected : ''} onClick={() => toggleContent()}>
                    소개
                </li>
            </div>

            {isAboutVisible ? (
                <>
                    <div className={styles.about_icon}>
                        <span>
                            <BsGithub />
                        </span>
                        <span>
                            <BsFillTelephoneFill />
                        </span>
                        <span>
                            <BsFillHouseFill />
                        </span>
                    </div>
                    <div className={styles.about_content}>
                        <p>간단 소개 어쩌구저쩌꾸~</p>
                    </div>
                </>
            ) : (
                <div className={styles.board}>
                    {user.map((a, i) => (
                        <div
                            className={styles.card}
                            key={i}
                            onClick={() => navigate(`/detail/${a.title}`)}
                        >
                            <h1>{a.title}</h1>
                            <h3>{a.content}</h3>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default About;
