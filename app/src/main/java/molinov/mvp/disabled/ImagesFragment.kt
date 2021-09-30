package molinov.mvp.disabled

//class ImagesFragment : MvpAppCompatFragment(), ImagesView, BackButtonListener {
//
//    private var _vb: FragmentImagesBinding? = null
//    private val vb get() = _vb!!
////    private val presenter by moxyPresenter {
////        ImagesPresenter(image, App.instance.router)
////    }
//    private val dir by lazy {
//        requireContext().getDir(Environment.DIRECTORY_PICTURES, Context.MODE_PRIVATE)
//    }
//    private val image by lazy {
//        Image(dir).createJpegFile(
//            ResourcesCompat.getDrawable(
//                resources,
//                R.drawable.nasa,
//                requireContext().theme
//            )
//        )
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _vb = FragmentImagesBinding.inflate(inflater, container, false)
//        return vb.root
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _vb = null
//    }
//
//    override fun backPressed(): Boolean {
//        return presenter.onBackPressed()
//    }
//
//    override fun init() {
//        vb.progressBar.isVisible = false
//        vb.cancelled.isVisible = false
//        vb.imagePng.isVisible = false
//        vb.imageJpeg.setImageBitmap(BitmapFactory.decodeFile(image.fileJPEG.absolutePath))
//        vb.back.setOnClickListener { presenter.onBackPressed() }
//        vb.convert.setOnClickListener {
//            if (!vb.imagePng.isVisible) {
//                vb.progressBar.isVisible = true
//                vb.cancelled.isVisible = false
//                presenter.convert()
//            } else Toast.makeText(
//                requireContext(),
//                "already converted\ndelete before convert",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//        vb.dismiss.setOnClickListener { presenter.dismiss() }
//        vb.delete.setOnClickListener { presenter.delete() }
//    }
//
//    override fun convert(file: File) {
//        vb.imagePng.setImageBitmap(BitmapFactory.decodeFile(file.absolutePath))
//        vb.progressBar.isVisible = false
//        vb.imagePng.isVisible = true
//    }
//
//    override fun dismiss() {
//        if (vb.progressBar.isVisible) {
//            vb.progressBar.isVisible = false
//            vb.cancelled.isVisible = true
//        }
//    }
//
//    override fun delete() {
//        if (!vb.progressBar.isVisible) {
//            vb.imagePng.setImageBitmap(null)
//            vb.imagePng.isVisible = false
//        }
//    }
//}
