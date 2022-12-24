<template>
  <div class="error">
    <div class="error__title">
      Помилка!!!!!
    </div>
    <div class="error__desc desc1">
      Будь ласка поверніться на головну сторінку.
    </div>
    <div class="error__desc desc2">
      Текст помилки:
    </div>
    <div class="error__msg">
      {{ message }}
    </div>
    <router-link to="/" class="error__homelink">
      На головну
    </router-link>
  </div>
</template>

<script>
export default {
  name: "Error",
  data() {
    return {
      message: null
    }
  },
  mounted() {
    if (this.$store.state.error.message) {
      this.message = this.$store.state.error.message
    } else {
      this.message = "404 Page not found"
    }
    this.$store.commit("error/setMessage", null)
  }
}

</script>

<style scoped>
.error {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
  height: 100%;
}

.error__title {
  font-size: 3rem;
  color: #FF0A00;
  margin-bottom: 15px;
}

.error__desc {
  font-size: 2.5rem;
  margin-bottom: 10px;
}


.desc1 {
  color: #FF7674;
}

.desc2 {
  align-self: flex-start;
  color: #FFB5BA;
}

.error__msg {
  align-self: flex-start;
  color: red;
  font-size: 1.5rem;
  margin-bottom: 5px;
  flex: 1 1 auto;
}

.error__homelink {
  color: #8DFF5C;
  align-self: flex-end;
  font-size: 2rem;
  transition: 0.3s;

}
.error__homelink:hover {
  color: #4FFF47;
}

.error__homelink:active {
  color: white;
}
@media (max-width: 1000px) {
  .error{
    padding: 10px 20px;
  }
  .error__title {
    font-size: 2rem;
  }

  .error__desc {
    font-size: 1.5rem;
    margin-bottom: 10px;
  }

  .error__msg {
    font-size: 1rem;
  }

  .error__homelink {
    font-size: 1rem;
  }

}
@media (max-width: 600px) {
  .error{
    padding: 10px 20px;
  }
  .error__desc {
    align-items: center;
    font-size: 1.2rem;
    margin-bottom: 10px;
  }

  .error__msg {
    font-size: 1rem;
  }

  .error__homelink {
    font-size: 1rem;

  }

}
</style>