import axios from "axios";

export const fetchCompanies = async () => {
  const response = await axios.get("http://localhost:8080/api/v1/backend-java-springboot/companies/list");
  return response.data;
};

export const deleteCompany = async (nit: string) => {
  await axios.delete(`http://localhost:8080/api/v1/backend-java-springboot/companies/${nit}`);
};

export const updateCompany = async (nit: string, data: any) => {
  await axios.put(`http://localhost:8080/api/v1/backend-java-springboot/companies/${nit}`, data);
};
