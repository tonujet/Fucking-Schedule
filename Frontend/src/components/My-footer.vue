<template>
  <div class="footer">
      <div class="footer__title">
        Наші соціальні мережі
      </div>
      <div class="footer__icons">
        <a href="#" class="footer__link">
          <fa :icon="['fab','facebook']" size="2x" style="color: #2E2BFF"/>
        </a>
        <a href="#" class="footer__link">
          <fa :icon="['fab','whatsapp']" size="2x" style="color: #52FF35"/>
        </a>
        <a href="#" class="footer__link">
          <fa :icon="['fab','youtube']" size="2x" style="color: red"/>
        </a>
        <a href="#" class="footer__link">
          <fa :icon="['fab','telegram']" size="2x" style="color: #205CFF"/>
        </a>
        <a href="#" class="footer__link">
          <fa :icon="['fab','twitter']" size="2x" style="color: #2072FF"/>
        </a>

      </div>
      <div class="footer__bottom">
        <a href="#" class="bottom__link">
          Privacy Police
        </a>
        <a href="#" class="bottom__link">
          About
        </a>
      </div>
  </div>
</template>

<script>
export default {
  name: "my-footer"
}
</script>

<style scoped>
.footer{
  display: flex;
  flex-direction: column;
  align-items: center;
}
.footer__title{
  margin-bottom: 7px;
  font-size: 1.2rem;
  color: white;
}
.footer__icons{
  margin-bottom: 7px;
  display: flex;
  flex-wrap: wrap;

}
.footer__link{
  padding: 5px;
  border-radius: 10px;
  margin: 0 10px;
  background: white;
  display: block;
  border: 3px solid #252526;
  transition: 0.3s;
}
.footer__link:hover{
  background: lightgrey;

}
.footer__link:active{
  background: #6E6E65;
}
.bottom__link{
  color: navajowhite;
  margin: 0 10px;
  transition: 0.2s;
}
.bottom__link:hover{
  color: cadetblue;
}
.bottom__link:active{
  color: dodgerblue;
}
.footer__bottom{

}
@media (max-width: 430px) {
  .footer__icons{
    justify-content: center;
  }
  .footer__link{
    margin-bottom: 5px;
    flex: 1 1 auto;
    text-align: center;
  }
}
</style>