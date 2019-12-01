//index.js
//获取应用实例
const app = getApp()

Page({
  globalData: {
    appid: 'wx2ee36363cd0328af',
    secret: '7c55b1add9984c4f6ebdae86ecd858fc',
  },
  data: {
    elements: [
      { title: '充值', name: 'recharge', color: 'purple', icon: 'moneybagfill' },
      { title: '添加记录', name: 'adjunction', color: 'grey', icon: 'formfill' },
      { title: '消费记录', name: 'lists', color: 'pink', icon: 'list' },
      { title: '绑定', name: 'locking', color: 'red', icon: 'newsfill' },
    ],
    card:null,
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },
  onLoad: function () {
    //判读是不是第一次登陆
    wx.showLoading({
      title: '内容加载中',
      mask: true,
    })
    this.setData({
      userInfo: wx.getStorageSync("userInfo"),
    })
   
    if (!wx.getStorageSync('openid').openid) {
      this.getopenid();
    }else{
      this.findByOpenid();
    }
    
    wx.hideLoading();

  },
  getopenid: function (e) {
    var that = this;
    //登录凭证校验。通过 wx.login() 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程。
    wx.login({
      success: function (res) {
        if (res.code) {
          console.log("res.code:" + res.code);
          var d = that.globalData;//这里存储了appid、secret、token串  
          var l = 'https://api.weixin.qq.com/sns/jscode2session?appid=' + d.appid + '&secret=' + d.secret + '&js_code=' + res.code + '&grant_type=authorization_code';
          wx.request({
            url: l,
            data: {},
            method: 'GET',
            success: function (res) {
              var obj = {};
              obj.openid = res.data.openid;
              obj.expires_in = Date.now() + res.data.expires_in;
              wx.setStorageSync('openid', obj);//存储openid 
              that.globalData.openid = obj.openid
              wx.setStorageSync('session_key', res.data.session_key);
              that.data.openid = obj.openid;
            },
            complete: function (res) {
              that.findByOpenid();
              wx.hideLoading();
            
            }
          });
        } else {
          console.log('获取用户登录态失败！' + res.errMsg)
        }
      }
    });
  },
  //获取用户信息
  getUserInfo: function (e) {
    var that = this;
    wx.getUserInfo({
      success: function (res) {
        console.log(res);
        that.setData({
          userInfo: res.userInfo
        })
        res.userInfo.openid=wx.getStorageSync("openid").openid;
        wx.setStorageSync('userInfo', res.userInfo)
        app.globalData.userInfo = res.userInfo

      },
      complete: function (res) {
        that.onLoad();
        that.updateUser(res.userInfo)
        // that.saveUser()
      }
    })
  },
  //从数据库中查找用户
  findByOpenid:function(){
   
      var that = this;
      wx.request({
        url: 'http://localhost:8080/schoolCard/user/findByOpenid',
        data: {
          openid: wx.getStorageSync("openid").openid
        },
        header: {
          "Content-Type": "application/x-www-form-urlencoded"
        },
        method: 'post',
        success: function (e) {
          if (e.data.avatarUrl) {
            wx.setStorageSync("userInfo", e.data);
            app.globalData.userInfo = e.data;
            that.setData({
              userInfo:e.data
            })
          }
        },
        complete: function (e) {
        if (e.data.cardId) {
            // console.log(e.data.cardId)
            that.countMoneyByCardId(e.data.cardId)
          }
          if (!e.data.nickName) {
            wx.showToast({
              title: '请先授权登陆',
              image: '/pages/logo/warn.png',
              duration: 3000,
              mask: true,

            })
          }
        }
      })
    
  },
  updateUser:function(e){
    console.log(e)
    var that = this;
    wx.request({
      url: 'http://localhost:8080/schoolCard/user/updateUser',
      data: {
        openid: wx.getStorageSync("openid").openid,
        nickName:e.nickName,
        avatarUrl:e.avatarUrl
      },
      header: {
        "Content-Type": 'application/json;charset=UTF-8'
      },
      method: 'post',
      success: function (e) {
        console.log(e.data)
      }
    })
  

  },
  countMoneyByCardId:function(e){
    console.log(e)
    var that = this;
    wx.request({
      url: 'http://localhost:8080/schoolCard/record/countMoneyByCardId',
      data: {
        cardId:e
      },
      header: {
        "Content-Type": "application/x-www-form-urlencoded"
      },
      method: 'post',
      success: function (e) {
        console.log(e);
          if(e.data.price<10){
            wx.showToast({
              title: '当前余额较低',
              image: '/pages/logo/warn.png',
              duration: 3000,
              mask: true,
            })
          }
        that.setData({
          card:e.data
        })
      }
    })

  },
  onShow:function(){
    if (wx.getStorageSync("userInfo").cardId && wx.getStorageSync("userInfo")!=''){
      this.countMoneyByCardId(wx.getStorageSync("userInfo").cardId)
    }
    
  }

})

