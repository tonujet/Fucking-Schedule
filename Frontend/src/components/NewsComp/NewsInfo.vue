<template>
  <FirstLayerBlock class="item">
    <div class="item__body">
      <div class="item__title">
        {{ item.title }}
      </div>
      <div class="item__time">
        {{new Date(item.dateCreated).toLocaleString()}}
      </div>
      <div class="item__img">
        <img :src="img" alt="Header img" class="img">
      </div>
      <div class="item__desc">
        {{ item.description }}
      </div>
      <div class="item__text">
        {{ item.text }}
      </div>
    </div>
    <div class="item__footer">
      <div class="item__links">
        <router-link
            class="item__link"
            @click="$store.commit('subject/setSubjectName', 'news')"
            :to="'data/news/update/'+this.item.id">Більше...
        </router-link>
        <router-link
            class="item__link"
            @click="setNews"
            :to="'data/news/update/'+this.item.id">Редактувати
        </router-link>
      </div>
    </div>
  </FirstLayerBlock>
</template>

<script>
export default {
  name: "NewsInfo",
  components: {},
  props: {
    item: {
      type: Object
    },
  },
  data(){
    return{
      img:null,
    }
  },
  methods:{
    setNews(){
      this.$store.commit('subject/setSubjectName', 'news')
      localStorage.setItem("subject", "news")
    }
  },
  mounted() {

      this.$load(() => {
        if(this.item.headerImgId){
          this.img = this.$api.img.downloadImage(this.item.headerImgId)
              .then((response) => {
                const reader = new window.FileReader();
                reader.readAsDataURL(response.data);
                reader.onload = () => {
                  this.img = reader.result;
                }
              });
        }
      })
    }
}
</script>

<style scoped>
.item {
  color: white;
  padding: 25px;
  border-radius: 25px;
  display: flex;
  flex-direction: column;
}

.item__body {
  flex: 1 1 auto;
}

.item_footer {
  flex: 0 0 auto;
}

.item__title {
  font-size: 2.5rem;
  color: wheat;
  margin-bottom: 4px;
}

.item__time {
  color: grey;
  font-size: 0.8rem;
  font-family: cursive;
  margin-bottom: 10px;
}

.item__desc {
  color: wheat;
  font-size: 1.5rem;
  margin-bottom: 20px;
}

.item__img {
  margin-bottom: 10px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.img{
  height: 300px;
}

.item__links {
  display: flex;
  justify-content: space-between;
}

.item__link {
  color: #B9FF8E;
}

.item__link:hover {
  color: wheat;
}

.item__link:active {
  color: lightgrey;
}

.item__text {
  color: white;
  margin-bottom: 10px;
}

@media (max-width: 900px) {
  .item__title {
    font-size: 2rem;
  }

  .item__time {
    font-size: 0.8rem;
  }

  .item__desc {
    font-size: 1.2rem;
  }

  .item__text {
    font-size: 0.9rem;
  }

  .item__link {
    font-size: 0.8rem;
  }
}
</style>