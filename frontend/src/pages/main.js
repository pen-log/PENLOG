import styles from '../css/main.module.scss';
import data from '../dummy/data'
import { useNavigate } from 'react-router-dom';

function Main() {
    const navigate = useNavigate()
    return (
        <div className={styles.main_container}>
            {data.map((a, i) => (
                <div className={styles.main_item} key={i}>
                    <div className={styles.main_img}>
                        <img src="velog.png" onClick={() => navigate('/detail/' + i)} />
                    </div>
                    <div className={styles.main_info}>
                        <div className={styles.main_title}>{a.title}</div>
                        <div className={styles.main_content}>{a.content}</div>
                        <div className={styles.main_period}>{a.period}</div>
                        <div className={styles.main_name}><span onClick={() => navigate('/user/@' + a.name)}>{a.name}</span></div>
                    </div>
                </div>
            ))}
        </div>
    )
}

export default Main
