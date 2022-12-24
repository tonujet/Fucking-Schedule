<template>
  <div class="component">
    <div class="item">
      <input
          type="file"
          class="file"
          @change="change"
      >
      <div
          class="button"
      >
        Обрати картинку
      </div>
    </div>
    <div class="preview">
      <img :src="currentFile" v-if="currentFile">
    </div>
  </div>
</template>

<script>
export default {
  name: "CRUDFile",
  data(){
    return{
      currentFile:"",
      isNull:this.isNull
    }
  },
  methods:{
    change(e){
      this.doEmit(e)
      this.setPreview(e);
    },
    doEmit(e){
      this.$emit('update:modelValue', e.target.files[0])
    },
    setPreview(e){
      const file = e.target.files[0]
      if(file){
        this.currentFile= URL.createObjectURL(file)
      }
    }
  },
  props:{
    isNull:{
      type:Boolean,
      required:true,
    }
  },
  watch:{
    isNull(){
      console.log(2)
      this.currentFile = ""
    }
  }
}
</script>

<style scoped>
  .component{
    display: flex;
    justify-content: space-between;
    height: 85px;
  }
  .item{
    position: relative;
    display: flex;
    align-items: center;
  }
  .file{
    position: absolute;
    top: 0;
    left: 0;
    width: 210px;
    height: 100%;
    opacity: 0;
    font-size: 0;
    cursor: pointer;
  }
  .button{
    display: inline-flex;
    border-radius: 30px;
    justify-content: center;
    align-items: center;
    background: #4FFF47;
    height: 50px;
    padding: 0 20px;
    font-weight: 700;
    color: black;
    margin-bottom: 3px;
  }
  .button:hover{
    background: red;
  }
  .preview{
    display: block;
    max-width: 100px;
    padding: 10px 0 0 0;
  }
  .preview img{
    height: 90%;
    border: 1px solid white;
    border-radius: 5px ;
  }
</style>