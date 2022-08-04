import axios,{AxiosInstance} from "axios";

class Api{
    private static axiosInstance: AxiosInstance;

    static init(){
        this.axiosInstance = axios.create({
            baseURL: 'http://run.mocky.io/v3/86b85cd1-fa7a-456d-b995-17150b3661a9'
        })
    }

    static async get<ResponseType>(url:string){
        return await Api.axiosInstance.get<ResponseType>(url)
    }

    static async post<ResponseType,DataType>(url:string, data?: DataType){
        return Api.axiosInstance.post<ResponseType,DataType>(url,data);
    }
}

export default Api;