var vue=new Vue({
    el: '#app',
    data:{
        keyword:"",
        commodities: [], //查询结果
        currentCommodity:{}, //当前编辑的商品
        dialogVisible: false, //对话框是否显示
        editMode:false  //当前是否是编辑模式（还是添加模式）
    },
    methods: {
        query: function (keyword) {
            var path='/commodities';
            if(this.keyword!="") path=path+"?name="+this.keyword;
            var self = this
            axios.get(path)
                .then(response=>self.commodities = response.data)
                .catch(e =>self.$message.error(e.response.data))
        },
        deleteCommodity: function (commodity) {
            var self = this
            axios.delete('/commodities/'+commodity.id)
                .then(response=>self.query())
                .catch(e =>self.$message.error(e.response.data))
        },
        showEdit:function(commodity){
            this.dialogVisible = true
            this.editMode=true;
            this.currentCommodity = Object.assign({},commodity)
        },
        showAdd:function(commodity){
            this.dialogVisible = true
            this.editMode=false;
            this.currentCommodity = {deleted:false}
        },
        saveCommodity:function(){
            var self = this
            if(self.editMode){  // 编辑模式
                axios.put('/commodities/'+self.currentCommodity.id + '?name=' + self.currentCommodity.name)
                    .then(response=> self.query())
                    .catch(e =>self.$message.error(e.response.data))
                console.log("currentCommodity.name = ", self.currentCommodity.name)
            }else{
                // post对应添加商品方法，参数1为地址，参数2为方法参数（商品对象）
                axios.post('/commodities',self.currentCommodity)  
                    .then(response=> self.query())
                    .catch(e => self.$message.error(e.response.data))
            }
            this.dialogVisible = false
        }
    }
})
vue.query();