<template>
  <div class="header">
    <div class="header__block">
      <div class="header__logo">
        <router-link to="/">
          <img src="../assets/img/iconfinder-white-logo-black-and-white.png" alt="Logo">
        </router-link>
      </div>
    </div>

    <div class="header__nav">
      <router-link class="header__link link1" to="/rozklad">Розклад</router-link>
      <router-link class="header__link link2" to="/news">Новини</router-link>
      <router-link class="header__link link3" to="/data">Дані</router-link>
    </div>
    <div class="header__account">
      <access-button class="header__button button1">Sign up</access-button>
      <access-button class="header__button button2">Log in</access-button>
    </div>
    <div class="header__menu-icon" @click="toggleDialogMenu">
      <fa
          :icon="['fas', 'bars']"
          :size="widthPage < 500 ? '2x' : '3x'"
          style="color: wheat"
      />
    </div>
    <div class="header__menu-body" v-if="isActive" @click.stop="toggleDialogMenu">
      <div class="menu-body__links">
        <router-link class="menu-body__link menu-body__link1" to="/rozklad">Розклад</router-link>
        <router-link class="menu-body__link menu-body__link2" to="/data">Дані</router-link>
        <router-link class="menu-body__link menu-body__link3" to="/news">Новини</router-link>
      </div>
      <div class="menu-body__account">
        <access-button class="menu-body__button menu-body__button1">Sign up</access-button>
        <access-button class="menu-body__button menu-body__button2">Log in</access-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "my-Header",
  components: {},
  data() {
    return {
      isActive: false,
      widthPage: window.screen.width,
    }
  },
  methods: {
    toggleDialogMenu(event) {
      this.isActive = !this.isActive
    },
  },
  mounted() {
    window.addEventListener('resize', () => {
      this.widthPage = window.screen.width
    })
  }

}
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;

}

.header__block {
  align-self: center;
  flex: 0 1 14%;
}

.header__logo {
  width: 100px;
  height: 100px;
  overflow: hidden;
  border-radius: 50%;
  border: 2px solid grey;
}

.header__logo img {
  width: 100%;
  height: 100%;
}

.header__nav {
  align-self: center;
}

.header__account {
  align-self: center;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
}

.header__button {
  flex: 0 1 auto;
  margin-left: 10px;
  transition: 0.2s;
  font-weight: bold;
  padding: 5px 10px;
  font-size: 1.4rem;
  color: black;
}

.button1,
.menu-body__button1 {
  background: #ACFF7D;
}

.button2,
.menu-body__button2 {
  background: #FF8B87;
}

.button1:hover,
.menu-body__button1:hover {
  background: #83FF52;
}

.button1:active,
.menu-body__button1:active {
  background: wheat;
}

.button2:hover,
.menu-body__button2:hover {
  background: #FF6B6B;
}

.button2:active,
.menu-body__button2:active {
  background: wheat;
}

.header__link {
  color: white;
  text-decoration: none;
  font-size: 1.8rem;
  padding: 20px;
  margin: 0 20px 0 40px;
  transition: 0.3s;
}

.header__link:hover {
  color: wheat;
  border-bottom: 1px solid white;
}

.header__link:active {
  color: white;
}

.header__menu-icon {
  display: none;
}

.header__menu-body {
  display: none;
}

@media (max-width: 1110px) {
  .header__account {
    flex-direction: column;
  }
}

@media (max-width: 860px) {
  .header__menu-icon {
    display: block;
    cursor: pointer;
    align-self: center;
    padding: 10px;
  }

  .header__account {
    display: none;
  }

  .header__block {
    flex: 0 0 100px;
  }

  .link1 {
    margin: 0;
    font-size: 2.5rem;
  }

  .link3 {
    display: none;
  }

  .link2 {
    display: none;
  }

  .header__menu-body {
    position: fixed;
    display: flex;
    top: 0;
    right: 0;
    width: 100%;
    height: 100%;
    z-index: 1001;
    background: rgba(0, 0, 0, 0.9);
    flex-direction: column;
    padding: 20px;
  }

  .menu-body__links {
    flex: 1 1 auto;
    display: flex;
    flex-direction: column;
    font-size: 3rem;
  }

  .menu-body__link {
    text-align: center;
    margin-bottom: 20px;
    padding: 20px;
    border-radius: 20px;
    background: #2f3136;
    color: wheat;
    text-decoration: none;
  }

  .menu-body__link1 {

  }

  .menu-body__link2 {
  }

  .menu-body__account {
    display: flex;
  }

  .menu-body__button {
    flex: 1 1 auto;
    padding: 20px;
    margin: 10px;
    font-size: 3rem;
  }

  .menu-body__button1 {

  }

  .menu-body__button2 {

  }

  @media (max-width: 600px) {
    .link1 {
      display: none;
    }

    .header__logo {
      width: 50px;
      height: 50px;
    }

    .menu-body__button {
      padding: 10px;
      margin: 10px;
      font-size: 1.5rem;
    }

    .menu-body__link {
      font-size: 1.4rem;
    }
  }
}
</style>