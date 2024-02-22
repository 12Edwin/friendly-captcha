<template>
  <div class="contain">
    <form @submit.prevent="submitForm()">
      <label for="name" class="mandatory-fields">Nombre: </label><br>
      <input type="text" v-model="formData.name" id="name">
      <div
          ref="container"
          class="frc-captcha"
          data-sitekey="FCMV636PBUUL9VC6"
          data-lang="es"
      >
      </div>

      <button type="submit" class="button">Enviar</button>
    </form>
  </div>
</template>

<script>
import { WidgetInstance } from 'friendly-challenge';
import { ref } from 'vue';
import {CaptchaService} from "@/services/resolveCaptcha";
export default {
  data(){
    return{
      container: ref("container"),
      widget: ref(),
      formData:{
        name: "",
        capchaToken: null
      }
    }
  },
  methods: {
    submitForm:()=>{
      if(this.formData.name){
        window.alert("El nombre es obligatorio")
      }
    },

    doneCallback: async (solution) => {
      let result = await CaptchaService.verificarCaptcha(solution)
      console.log(result);
    },

    async verifyCaptcha (solution){


    },

    errorCallback: (err) => {
      console.log('There was an error when trying to solve the Captcha.');
      console.log(err);
    }
  },
  mounted(){
    if(this.$refs.container){
      this.widget = new WidgetInstance(
          this.$refs.container, {
            startMode: "auto",
            doneCallback: this.doneCallback,
            errorCallback: this.errorCallback
          }
      )
    }
  },
  beforeDestroy(){
    if (this.widget) {
      this.widget.destroy()
    }
  }
}
</script>

<style scoped>
.contain{
  border: solid 1px black;
  margin: 30px;
  padding: 30px;
}

.mandatory-fields:after {
  content: "* ";
  color: red
}

.button{
  background-color: black;
  color: white;
  border: black;
  margin-top: 10px;
}

</style>