# FastAPI笔记



## 1.路由分发

![屏幕截图(1009)](https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1009).png)



![屏幕截图(1010)](https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1010).png)

代码演示

```python
main.py文件

import uvicorn
from fastapi import FastAPI
from api.book import book_router
from api.author import author_router

app = FastAPI()

app.include_router(book_router,prefix="/book",tags=["图书"]) #prefix表示以/book结尾的都取找                                                                 book_router子路由
app.include_router(author_router,prefix="/author",tags=["作者"])



@app.get("/")
async def main():
    return {"Hello": "Worldkitty"}

@app.get("/text")
async def main():
    return "hello FastAPI"

@app.get("/json")
async def main():
    return {"Hello": "json"}

@app.get("/java")
async def main():
    return {"Hello": "java"}

#路径参数(动态路由)
@app.get("/user/{username}")
async def main(username: str):
    return f"the user's name is : {username}"

#查询参数
# http://127.0.0.1:8000/search?keyword=''
@app.get("/search")
async def read_item(keyword: str = ""):
    return f"用户查询了{keyword}"

if __name__ == "__main__":
   import uvicorn
   uvicorn.run("main:app", host="127.0.0.1", port=8000, reload=True)
    
  
book.py文件

from fastapi import APIRouter

book_router = APIRouter()

@book_router.get("/get")
async def book():
    return {"book": "book"}

@book_router.post("/post")
async def book():
    return {"book": "post"}    
```



## 2.form表单

![屏幕截图(1011)](https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1011).png)



## 3.request

### 1.如果写了重复的路由:

写定了的要写在自匹配的前面如下图的 @app.get("/me/xx") 要写在@app.get("/me/{item_id}") 的前面

![dd](https://cdn.jsdelivr.net/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1016).png)



### 2.查询参数

```python
# -*- coding: UTF-8 -*-
from fastapi import FastAPI

app = FastAPI()
fake_items_db = [{"item_name": "Foo"}, {"item_name": "Bar"}, {"item_name": "Baz"}]

# http://127.0.0.1:8000/items/?skip=1&limit=2
@app.get("/items/")
async def read_item(skip: int = 0, limit: int = 10):
    return fake_items_db[skip : skip + limit]

@app.get("/i/")
async def i(A: str = 'HI..', B: str = 'Hello..', C: str = 'He..'):
    return {'cc': A+B+C},{'dd': B+C}
    
@app.get("/ii/")
async def ii(A: int = 0, B: int = 10, C: int = 20):
    return {'cc': A+B+C},{'dd': B+C}

@app.get("/iii/")
async def iii(A: int = 0, B: int = 10, C: int = 20):
    return 'A+B+C',A+B+C
   
    
# bool与类型转换
#http://127.0.0.1:8000/xxx/item_id=ooopp?SS=True
@app.get("/xxx/{item_id}")
async def xxx(item_id: str, QQ: str = None, SS: bool = False):
    item = {"item_id": item_id}
    if QQ:
        item.update({"QQ": QQ})
    if not SS:  # 如果SS是假
        item.update(
            {"item_id": "This is SSSSSSS"}
        )
    return item

#多路径 和 查询参数 和 必填字段
@app.get("/user/{user_id}/item/{item_id}")
async def read_user_item(
    user_id: int, item_id: str, q: str = None, short: bool = False
):
    item = {"item_id": item_id, "owner_id": user_id}
    if q:
        item.update({"q": q})
    if not short:
        item.update(
            {"description": "This is an amazing item that has a long description"}
        )
    return item


if __name__ == '__main__':
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000)

```

### 3. url规范：选填和必填

```python
#下面xxx函数的参数如果没有写默认值，那么在路径和查询参数中必填，不然会报错  
@app.get("/xxx/{item_id}")
async def xxx(item_id: str, QQ: str , SS: bool = True):
    item = {"item_id": item_id}
    if QQ:
        item.update({"QQ": QQ})
    if not SS:  # 如果SS是假
        item.update(
            {"item_id": "This is SSSSSSS"}
        )
    return item
```

### 4.路径参数(Path)和查询参数(Query)和数值校验

```python
# -*- coding: UTF-8 -*-
from fastapi import FastAPI, Path, Query

app = FastAPI()


@app.get("/items/{item_id}")
async def read_items(
    item_id: int = Path(..., title="The ID of the item to get", ge=50, le=100),#ge 大于等于 gt是大于 le是小于等于
    q: str       = Query(None, alias="item-query"),
    size: float  = Query(1, gt=0, lt=10.5)
                    ):
    results = {"item_id": item_id}
    if q:
        results.update({"q": q})
    return results



if __name__ == '__main__':
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000)

```



### 5.多参数body混合

例子1：

```python
# -*- coding: UTF-8 -*-
from fastapi import FastAPI, Path
from pydantic import BaseModel

app = FastAPI()


class Item(BaseModel):
    name: str
    description: str = None
    price: float
    tax: float = None

# 混合参数
@app.put("/items/{item_id}")
async def update_item(
    *,
    item_id: int = Path(..., title="The ID of the item to get", ge=0, le=1000),
    q: str = None,
    item: Item = None,
):
    results = {"item_id": item_id}
    if q:
        results.update({"q": q})
    if item:
        results.update({"item": item})
    return results

    
if __name__ == '__main__':
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000)

```

例子2：（将  importance: int = Body(...)  嵌入请求体中 ，即将非body类嵌入请求体中）

```python
# -*- coding: UTF-8 -*-
from fastapi import Body, FastAPI
from pydantic import BaseModel

app = FastAPI()


class Item(BaseModel):
    name: str
    description: str = None
    price: float
    tax: float = None


class User(BaseModel):
    username: str
    full_name: str = None

# body的奇异值
@app.put("/items/{item_id}")
async def update_item(
    *, item_id: int, item: Item, user: User, importance: int = Body(...) //嵌入请求体中
):
    results = {"item_id": item_id, "item": item, "user": user, "importance": importance}
    return results


    
if __name__ == '__main__':
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000)

```

例子3：

嵌入单一的body（体现在请求体中）

```python
# -*- coding: UTF-8 -*-
from fastapi import Body, FastAPI
from pydantic import BaseModel

app = FastAPI()


class Item(BaseModel):
    name: str
    description: str = None
    price: float
    tax: float = None

#嵌入一个单body的参数
@app.put("/items/{item_id}")
async def update_item(*, item_id: int, item: Item = Body(..., embed=True)):
    results = {"item_id": item_id, "item": item}
    return results


    
if __name__ == '__main__':
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000)

```

例子4：

```python
# -*- coding: UTF-8 -*-
from fastapi import Body, FastAPI
from pydantic import BaseModel

app = FastAPI()


class Item(BaseModel):
    name: str
    description: str = None
    price: float
    tax: float = None


class User(BaseModel):
    username: str
    full_name: str = None

# 多主体参数和查询
@app.put("/items/{item_id}")
async def update_item(
    *,
    item_id: int,
    item: Item,
    user: User,
    importance: int = Body(..., gt=0),
    q: str = None
):
    results = {"item_id": item_id, "item": item, "user": user, "importance": importance}
    if q:
        results.update({"q": q})
    return results


    
if __name__ == '__main__':
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000)


```

### 6.Field验证 以及example字段

```python
# -*- coding: UTF-8 -*-
from fastapi import Body, FastAPI
from pydantic import BaseModel, Field

app = FastAPI()


class Item(BaseModel):
    name: str
    description: str = None
    price: float     = Field(..., gt=0)
    tax: float       = None


@app.put("/items/{item_id}")
async def update_item(
    *,
    item_id: int,
    item: Item = Body(...,
        example={   # example是Body里没有的字段；不会添加任何验证，而只会添加注释；不是example也不行
            "name": "Foo",
            "description": "A very nice Item",
            "price": 0,
            "toooo": 3.2,
            # "toooooooooo": 3.2, # 超过的键值对，会全部显示原来的Item
        },
    )
):
    results = {"item_id": item_id, "item": item}
    return results

    
if __name__ == '__main__':
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000)


```

### 7.模型嵌套(就是一个类里面的属性是另一个类)

```python
# -*- coding: UTF-8 -*-
from typing import Set,List
from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI()


class Image(BaseModel):
    url: str
    name: str


class Item(BaseModel):
    name: str
    description: str = None
    price: float
    tax: float = None
    tags: Set[str] = set()  # 集合 创建一个空集合必须用 set() 而不是 { }
    image: Image = None # 使用子模型作为类型
    images: List[Image] = None # 带有子模型列表的属性


# 到处都有编辑器支持
@app.put("/items/{item_id}")
async def update_item(*, item_id: int, item: Item):
    results = {"item_id": item_id, "item": item}
    return results
    


if __name__ == '__main__':
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000)
    
    
    


```

### 8.Cookie和Header

导入和声明Cookie参数(必须使用Cookie声明Cookie参数,不然该参数会被解释为查询参数)

```python
from typing import Annotated

from fastapi import Cookie, FastAPI

app = FastAPI()


@app.get("/items/")
async def read_items(ads_id: Annotated[str | None, Cookie()] = None):
    return {"ads_id": ads_id}
```

导入和声明Header参数

```python
from typing import Annotated

from fastapi import FastAPI, Header

app = FastAPI()


@app.get("/items/")
async def read_items(user_agent: Annotated[str | None, Header()] = None):
    return {"User-Agent": user_agent}
```



## 4. response

### 1.response模型

你可以在任意的*路径操作*中使用 `response_model` 参数来声明用于响应的模型：

在 FastAPI 路由装饰器 `@app.post("/items/", response_model=Item)` 中，`response_model=Item` 是一个非常重要的参数，它定义了 API 端点返回响应的数据模型和结构,如:

```python
# -*- coding: UTF-8 -*-
from fastapi import FastAPI
from pydantic import BaseModel, EmailStr

app = FastAPI()


class UserIn(BaseModel):
    username: str
    password: str
    email: EmailStr
    full_name: str = None


class UserOut(BaseModel):
    username: str
    email: EmailStr
    full_name: str = None


@app.post("/user/", response_model=UserOut)
async def create_user(*, user: UserIn):
    return user


# pip install pydantic[email]
if __name__ == '__main__':
    import uvicorn
    uvicorn.run(app, host="127.0.0.1", port=8000)
```

