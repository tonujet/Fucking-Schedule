<template>
  <div class="downArrow" @click.stop="sortByTime">
    <div class="downArrow__title" >
      <slot></slot>
    </div>
    <div class="downArrow__content">
      <a ref="arrow" class="arrow-icon">
        <span class="left-bar"></span>
        <span class="right-bar"></span>
      </a>
    </div>
  </div>
</template>

<script>
export default {
  name: "DownArrow",
  data(){
    return{
      isActive:false,
    }
  },
  methods:{
    sortByTime(event){
      this.$refs.arrow.classList.toggle('open')
      if(!this.isActive){
        this.$emit("sort", this.sortParams[1])
        this.isActive = true
      }else{
        this.$emit("sort", this.sortParams[0])
        this.isActive = false
      }

    }
  },
  props:{
    sortParams:[Array]
  },
}
</script>

<style scoped>
.downArrow{
  cursor: pointer;
}
.downArrow__title{
  text-align: center;
  color: #FF9E9B;
  margin-bottom: 10px;
  font-size: 1.5rem;
}
.downArrow__content{

}
.arrow-icon {
  margin: 0 auto;
  width: 70px;
  height: 50px;
  display: block;
  position: relative;
  cursor: pointer;
  border-radius: 4px;
}

.left-bar {
  position: absolute;
  background-color: transparent;
  top: 15px;
  left: 0px;
  width: 40px;
  height: 10px;
  display: block;
  transform: rotate(35deg);
  float: right;
  border-radius: 2px;
}
.left-bar:after {
  content: "";
  background-color: white;
  width: 40px;
  height: 10px;
  display: block;
  float: right;
  border-radius: 6px 10px 10px 6px;
  transition: all 0.5s cubic-bezier(0.25, 1.7, 0.35, 0.8);
  z-index: -1;
}

.right-bar {
  position: absolute;
  background-color: transparent;
  top: 15px;
  left: 26px;
  width: 40px;
  height: 10px;
  display: block;
  transform: rotate(-35deg);
  float: right;
  border-radius: 2px;
}
.right-bar:after {
  content: "";
  background-color: white;
  width: 40px;
  height: 10px;
  display: block;
  float: right;
  border-radius: 10px 6px 6px 10px;
  transition: all 0.5s cubic-bezier(0.25, 1.7, 0.35, 0.8);
  z-index: -1;
}

.open .left-bar:after {
  transform-origin: center center;
  transform: rotate(-70deg);
}
.open .right-bar:after {
  transform-origin: center center;
  transform: rotate(70deg);
}
@media (max-width: 700px) {
  .downArrow__title{
    font-size: 1.3rem;
  }
}
</style>