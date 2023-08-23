import styles from '../css/main.module.scss';

function Main() {
    const 상품 = ['1', '2', '3', '4', '5', 1, 2, 123]
    return (
        <div className={styles.main_container}>
            {상품.map((a, i) => (
                <div className={styles.main_item}>
                    <img src="/PENLOG/frontend/public/logo.png" />
                    <div className={styles.main_info}>
                        <h4>{a}</h4>
                        <span>글~</span>
                    </div>
                </div>
            ))}
        </div>
    )
}

export default Main
