import { useParams } from "react-router-dom"
import data from "../dummy/data"

function Detail() {
    const { id } = useParams()
    const FindItem = data.find(x => x.id == id)
    return (
        <>
            상단 url 디테일페이지
            <span>{FindItem.id}</span>
        </>
    )
}

export default Detail
