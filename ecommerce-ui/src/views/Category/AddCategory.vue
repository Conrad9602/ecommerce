<template>
    <div class="container">
      <div class="row">
        <div class="col-12 text-center">
          <h3 class="pt-3">Add new Category</h3>
        </div>
      </div>
  
      <div class="row">
        <div class="col-3"></div>
        <div class="col-md-6 px-5 px-md-0">
          <form @submit.prevent="addCategory">
            <div class="form-group">
              <label>Category Name</label>
              <input type="text" class="form-control" v-model="categoryName" required>
            </div>
            <div class="form-group">
              <label>Description</label>
              <input type="text" class="form-control" v-model="description" required>
            </div>
            <div class="form-group">
              <label>Image URL</label>
              <input type="url" class="form-control" v-model="imageURL" required>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
          </form>
        </div>
        <div class="col-3"></div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import swal from 'sweetalert';
  
  export default {
    data() {
      return {
        categoryName: "",
        description: "",
        imageURL: "",
      };
    },
    methods: {
      async addCategory() {
        console.log("🔹 Submit button clicked!");
  
        console.log("🔹 Category Data to be Sent:", {
          categoryName: this.categoryName,
          description: this.description,
          imageURL: this.imageURL
        });
  
        if (!this.categoryName || !this.description || !this.imageURL) {
          swal({
            text: "All fields are required!",
            icon: "warning",
          });
          console.error("❌ Validation Failed: Missing required fields.");
          return;
        }
  
        const newCategory = {
          categoryName: this.categoryName,
          description: this.description,
          imageUrl: this.imageURL, // Ensure it matches backend field
        };
  
        const baseURL = "http://localhost:8080/";
  
        try {
            console.log("🔹 Sending API request to:", baseURL + "category/create");
            console.log("🔹 Payload being sent:", JSON.stringify(newCategory));

            const response = await axios.post(baseURL + "category/create", newCategory, {
            headers: { "Content-Type": "application/json" },
            });

            console.log("✅ API Response:", response.data);

            swal({
            text: "Category Added Successfully!",
            icon: "success",
            closeOnClickOutside: false,
            });

            this.categoryName = "";
            this.description = "";
            this.imageURL = "";

        } catch (error) {
            console.error("❌ API Error:", error);

            if (error.response) {
            console.error("❌ Backend Response:", error.response.data);
            swal({
                text: "Failed to add category! Error: " + (error.response.data.message || "Unknown error"),
                icon: "error",
            });
            } else if (error.request) {
            console.error("❌ No response from server. Possible CORS issue or server is down.");
            console.error("🔹 Axios Error Details:", error.toJSON());
            swal({
                text: "No response from server! Check if backend is running.",
                icon: "error",
            });
            } else {
            console.error("❌ Unexpected Error:", error.message);
            swal({
                text: "Unexpected Error: " + error.message,
                icon: "error",
            });
            }
        }
        }
    }
  };
  </script>
  
  <style scoped>
  h4 {
    font-family: 'Roboto', sans-serif;
    color: #484848;
    font-weight: 700;
  }
  </style>
  