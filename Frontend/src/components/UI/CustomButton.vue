<template>
  <button class="button"><slot></slot></button>
</template>

<script>
export default {
  name: "CustomButton"
}
</script>

<style scoped>
.button{
  width: 90%;
  padding: 7px;
  border-radius: 5px 5px 30px 30px;
  font-weight: bold;
  color: black;
  box-shadow: 1px 1px 2px black;
  transition: 0.3s;
}
.updateButton{
  background: #78FF76;
}
.updateButton:hover{
  background: #4FFF47;
}
.updateButton:active{
  border: 2px solid white;
}
.addButton{
  background: wheat;
}
.addButton:hover{
  background: #FFCF6E;
}
.addButton:active{
  border: 2px solid white;
}
</style>