# 2.Webpack 적용

### # 구현 내용
* Webpack 적용
  * Babel 적용 (ES6)
  * CSS, SCSS 적용 
  * 이미지 압축 처리
  * CleanWebpackPlugin 적용(빌드시 기존 DIST 삭제 처리)
  * BannerPlugin 적용(빌드시 배너 생성)
  * Define Plugin 적용(공통 변수 예제)

<hr/>
  
### # 사용방법
dependencies를 설치해야 한다. npm이 설치된 상태에서 터미널에서 아래 구문을 실행 시킨다.

```
npm install
```

js나 css를 수정한 후에 웹팩에 적용을 하려면 아래 구믄을 실행 시킨다.

```
npx webpack 
```
또는

```
npm run build
```

dist 폴더가 생성되고 config.js에 js와 css가 포함된채 생성된다. 이미지가 있다면 압축된 이미지가 생성이 된다.

<hr/>

### # 권장사항
공통적으로 사용하는 js나 css를 import 하고 그 외에 화면단에서는 따로 처리하는 걸 추천한다.
해당 파일들을 압축을 하려면 webpack.config.js를 수정하면 된다.






