import axios,{AxiosInstance} from "axios";

class Api2{
    private static axiosInstance: AxiosInstance;

    static init(){
        this.axiosInstance = axios.create({
            baseURL: ' http://localhost:8391/dispatching/getByGasStationId'
        })
    }

    static async get<ResponseType>(url:string){
        return await Api2.axiosInstance.get<ResponseType>(url)
    }

    static async post<ResponseType,DataType>(url:string, data?: DataType){
        return Api2.axiosInstance.post<ResponseType,DataType>(url,data);
    }
}

export default Api2;