import axios from "axios";

export const fetchCategories = async () => {
  const response = await axios.get("http://localhost:8080/api/v1/backend-java-springboot/categories/list");
  return response.data;
};

export const fetchCompanies = async () => {
  const response = await axios.get("http://localhost:8080/api/v1/backend-java-springboot/companies/list");
  return response.data;
};

export const createProduct = async (data: any) => {
  const response = await axios.post("http://localhost:8080/api/v1/backend-java-springboot/products", data);
  return response.data;
};
